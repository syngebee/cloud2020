package com.itheima.cloud.alibaba.service.impl;

import com.itheima.cloud.alibaba.dao.StorageDao;
import com.itheima.cloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Autowired
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->开始扣减库存");
        storageDao.decrease(productId,count);
        LOGGER.info("------->扣减库存成功");
    }
}
