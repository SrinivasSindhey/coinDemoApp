package com.adp.coinconvertor.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adp.coinconvertor.util.CoinUtil;

@Component
public class CoinService {

	@Autowired 
	private CoinUtil coinUtil;
	
	private Logger logger = LoggerFactory.getLogger(CoinService.class);
	 
	public String convertBillToCoins(String paramCurrencyValue) {
		StringBuffer sb = new StringBuffer();
		try {
			Double tempCurrencyValue =  Double.valueOf(paramCurrencyValue+"");
			Integer currencyValue = Integer.parseInt(paramCurrencyValue);
		
			if(coinsExists(currencyValue)) {
				List<Double> denominations = coinUtil.getValueDenominations();
				for (int i = denominations.size()-1; i > -1; i--) {
					int reqCount = (int) (tempCurrencyValue / Double.valueOf(denominations.get(i))); 
					int remainingCount = coinUtil.getDenominationCounts(Double.valueOf( denominations.get(i)));
					
					if(reqCount<=remainingCount) {
						sb.append(denominations.get(i) + "coins " + reqCount );
						coinUtil.updateCoinCounts(denominations.get(i), remainingCount-reqCount);
						break;
						
					}
					else {
						sb.append( denominations.get(i) + "coins " + remainingCount +";");
						tempCurrencyValue = tempCurrencyValue - (remainingCount*Double.valueOf(denominations.get(i)));
					}
					if(reqCount<remainingCount) {
						coinUtil.updateCoinCounts(denominations.get(i), remainingCount-reqCount);
					}
					else {
						coinUtil.updateCoinCounts(denominations.get(i), 0);
					}
				}
			}
			else {
				return "No enough coins for exchange";
			}
			logger.info(currencyValue + " needs below coins");
			logger.info(sb.toString());
			return sb.toString();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			return "Invalid String" ;
		}
	}
	
		
	private boolean coinsExists(Integer currencyValue) {
		Double x = 0.0;
		Map<Double, Integer> currentConfig = coinUtil.getCoinDenominations();
		Iterator<Map.Entry<Double, Integer>> remainingVal = currentConfig.entrySet().iterator();
		while (remainingVal.hasNext()) {
			Double key = remainingVal.next().getKey();
			Integer val = currentConfig.get(key);
			x = x + key * val;
		}
		if (x>=currencyValue ) {
			return Boolean.TRUE;
		} 
		else {
			return Boolean.FALSE;
		}
	}
		
		
}
