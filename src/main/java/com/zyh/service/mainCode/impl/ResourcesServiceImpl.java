package com.zyh.service.mainCode.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zyh.dao.base.MybatisMapper;
import com.zyh.dao.mainCode.ResourcesMapper;
import com.zyh.domain.mainCode.Resources;
import com.zyh.service.base.impl.BaseServiceImpl;
import com.zyh.service.mainCode.IResourcesService;

/**
 * Service层： ResourcesServiceImpl->URL资源信息的操作接口实现
 *
 * @author 1101399
 * @CreateDate 2018-1-26 下午5:15:02
 */
@Service
public class ResourcesServiceImpl extends BaseServiceImpl<Resources, Integer> implements
        IResourcesService {

    private ResourcesMapper resourcesMapper;

    @Override
    @Resource(name = "mainCode.ResourcesMapper")
    public void setMybatisMapper(MybatisMapper<Resources, Integer> entityMapper) {
        this.resourcesMapper = (ResourcesMapper) entityMapper;
        this.mybatisMapper = entityMapper;
    }

}
