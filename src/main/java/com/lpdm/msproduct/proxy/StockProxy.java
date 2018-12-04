package com.lpdm.msproduct.proxy;

import com.lpdm.msproduct.entity.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(name="ms-stock",url="localhost:28086")
public interface StockProxy {

    @RequestMapping(value = "/stocks/{id}")
    Stock findById(@PathVariable(value = "id") int id);
}
