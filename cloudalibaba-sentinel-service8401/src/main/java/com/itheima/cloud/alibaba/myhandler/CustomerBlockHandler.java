package com.itheima.cloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.itheima.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult handerException(BlockException exception){
        return new CommonResult(4444,"按照客户自定义的globalExceptionHandler-----------1");
    }
    public static CommonResult handerException2(BlockException exception){
        return new CommonResult(4444,"按照客户自定义的globalExceptionHandler-----------2");
    }
}
