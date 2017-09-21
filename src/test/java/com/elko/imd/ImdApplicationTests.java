package com.elko.imd;

import com.elko.imd.configuration.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {AppConfig.class})
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")  
public class ImdApplicationTests {

	@Test
	public void contextLoads() {
	}

}
