package com.zyh.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.zyh.domain.base.DataSourceContextHolder;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-servlet.xml",
		"classpath:cxf-client.xml" })
@ActiveProfiles("development")
@Transactional("transactionManager")
@Rollback
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class mainxx{

    private static final Logger log = LoggerFactory.getLogger(mainxx.class);
//    @Autowired
//    public TXTFileMapper txtFileMapper;


	@Test
	public void main(){
//	    txtFileMapper.findDataById(32);
//	    DataSourceContextHolder.setDbType(DataSourceType.SLAVE);
	    log.info("dataSource:",DataSourceContextHolder.getDbType());

	}
}
