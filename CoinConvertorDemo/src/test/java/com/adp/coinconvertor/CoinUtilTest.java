package com.adp.coinconvertor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adp.coinconvertor.util.CoinUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoinUtilTest {

	@Autowired
	CoinUtil coinUtil;
	
	@Test
	public void testInitializeCoinDenomination() {
		assertEquals(4, coinUtil.getCoinDenominations().size());
	}
	
}
