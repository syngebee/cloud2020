package com.itheima.cloud.alibaba.dao;

import org.apache.ibatis.annotations.Param;

public interface StorageDao {
    void decrease(@Param("productId")Long productId,@Param("count")Integer count);
}
