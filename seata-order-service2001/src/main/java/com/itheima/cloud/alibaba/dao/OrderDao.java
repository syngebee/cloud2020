package com.itheima.cloud.alibaba.dao;

import com.itheima.cloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderDao {

    void create(Order order);

    void update(@Param("userId")Long userId,@Param("Integer")Integer status);

}
