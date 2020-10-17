package com.itheima.cloud.alibaba.controller;

import com.itheima.cloud.alibaba.domain.CommonResult;
import com.itheima.cloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    StorageService storageService;

    @PostMapping("/storage/decrease")
    CommonResult decrease(@RequestParam("porductId") Long productId, @RequestParam("count") Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"库存减少成功");
    }

}
