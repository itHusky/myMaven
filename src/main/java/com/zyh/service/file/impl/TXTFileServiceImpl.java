package com.zyh.service.file.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.dao.file.TXTFileMapper;
import com.zyh.domain.base.DataSource;
import com.zyh.domain.base.DataSourceType;
import com.zyh.domain.file.TXTFile;
import com.zyh.service.base.impl.BaseServiceImpl;
import com.zyh.service.file.ITXTFileService;

@Service
public class TXTFileServiceImpl extends BaseServiceImpl<TXTFile, Integer> implements ITXTFileService{

    private static final Logger log = LoggerFactory.getLogger(TXTFileServiceImpl.class);
    private TXTFileMapper txtFileMapper;

    @Override
    @Resource(name = "file.TXTFileMapper")
    public void setMybatisMapper(MybatisMapper<TXTFile, Integer> entityMapper) {
        // TODO Auto-generated method stub
        this.txtFileMapper = (TXTFileMapper) entityMapper;
        this.mybatisMapper = entityMapper;
    }

    @Override
    @DataSource(DataSourceType.SLAVE)
    public TXTFile findByName() {
        String name = "test";

        try{
            /**

System.exit(0)是将你的整个虚拟机里的内容都停掉了 ，而dispose()只是关闭这个窗口，
但是并没有停止整个application exit() 。无论如何，内存都释放了！也就是说连JVM都关闭了，
内存里根本不可能还有什么东西
System.exit(0)是正常退出程序，
而System.exit(1)或者说非0表示非正常退出程序
System.exit(status)不管status为何值都会退出程序。
和return 相比有以下不同点：   return是回到上一层，而System.exit(status)是回到最上层

             */
//            System.exit(0);
        }finally{

        }

        return txtFileMapper.findByName(name);
    }

    @Override
    public TXTFile findDataById(Integer fileid) {
        log.info("执行测试.........................");
        log.info("执行测试...",DataSourceType.SLAVE);
        return txtFileMapper.findDataById(fileid);
    }


}
