package com.adp.coinconvertor;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adp.coinconvertor.service.CoinService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillChangeApplicationTests {

	@Autowired
	CoinService coinService;
	
	Logger logger = LoggerFactory.getLogger(BillChangeApplicationTests.class);

	
	@Test
	public void testConvertBillToCoins_Invalid() {
		String res = coinService.convertBillToCoins("adf");
		assertEquals("Invalid String" , res);
	}
	
	@Test
	public void testConvertBillToCoins_Valid() {
		String res = coinService.convertBillToCoins("17");
		assertEquals("0.25coins 68", res);
	}

	@Test
	public void testConvertBillToCoins_LargeValue() {
		String res = coinService.convertBillToCoins("170");
		assertEquals("No enough coins for exchange" , res);
	}
	
	@Test
	public void testConvertBillToCoins_DecimalValue() {
		String res = coinService.convertBillToCoins("1.75");
		assertEquals("Invalid String" , res);
	}
}
