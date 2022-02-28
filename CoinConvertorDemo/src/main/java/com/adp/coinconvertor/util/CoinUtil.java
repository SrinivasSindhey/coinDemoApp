package com.adp.coinconvertor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.adp.coinconvertor.service.CoinService;

@Component
public class CoinUtil {
	
	@Value("${coin.denominations}")
	private String denominations;
	
	@Value("${coin.count}")
	private String count;
	
	private Map<Double, Integer> coinDenominations = new TreeMap<>();
	
	private Logger logger = LoggerFactory.getLogger(CoinService.class);
	
	private Map<Double, Integer> initializeCoinDenomination() {
		String tokens[] = denominations.split(",");
		for(int i=0;i<tokens.length;i++) {
			coinDenominations.put(Double.valueOf(tokens[i]), Integer.parseInt(count));
		}
		return coinDenominations;
	}
	
	
	public void updateCoinCounts (double coinVal, int updateCount) {
		if(coinDenominations.containsKey(coinVal)) {
			 coinDenominations.put(coinVal, updateCount);
		}
		logger.info("Remaining coins " + coinDenominations);
	}
	
	public Integer getDenominationCounts(double coinVal) {
		if(coinDenominations.containsKey(coinVal)) {
			return coinDenominations.get(coinVal);
		}
		return 0;
	}
	
	
	public List<Double> getValueDenominations() {
		return new ArrayList<>(coinDenominations.keySet());
	}
	
	
	public Map<Double, Integer> getCoinDenominations() {
		if(coinDenominations.isEmpty()) {
			coinDenominations = initializeCoinDenomination();
		}
		return coinDenominations;
	}
	
}
