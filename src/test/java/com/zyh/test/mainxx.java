package com.zyh.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-servlet.xml",
		"classpath:cxf-client.xml" })
@ActiveProfiles("development")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class mainxx{

//	@Autowired
//	public ReadFileController read;

	@Test
	public void main(){
//		String xx = read.list();
//		System.out.println(xx);
	}
}
