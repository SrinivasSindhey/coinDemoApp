package com.adp.coinconvertor.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.adp.coinconvertor.service.CoinService;

@RestController
public class CoinController {
	
	@Autowired
	CoinService coinService;

	@GetMapping("coins/{billValue}")
	public String getCoins(@PathVariable String billValue) {
		if(StringUtils.trimToNull(billValue)!=null) {
			try {
				int currencyValue = Integer.parseInt(billValue);
				if(currencyValue<1) {
					return ("Please enter a valid bill Value : " + currencyValue);
				}
			}catch(NumberFormatException nfe) {
				return ("Please enter valid numeric value : " + billValue);
			}
		}
		else {
			return ("Bill value cannot be empty " + billValue);
		}
		return coinService.convertBillToCoins(billValue) ;
	}
}
