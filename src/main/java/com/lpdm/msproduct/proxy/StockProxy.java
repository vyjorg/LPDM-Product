package com.lpdm.msproduct.proxy;

import com.lpdm.msproduct.entity.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name="ms-stock",url="localhost:28086")
public interface StockProxy {

    @RequestMapping(value = "/stocks/{id}")
    Stock findById(@PathVariable(value = "id") int id);

    @RequestMapping(value = "/stocks")
    List<Stock> listStock();

    @RequestMapping(value = "/stocks")
    Stock addStock(@RequestBody Stock stock);

    @RequestMapping(value="/stocks/{id}")
    void deleteStock(@PathVariable(value = "id") int id);

    @RequestMapping(value="/stocks")
    Stock updateStock(@RequestBody Stock stock);

}
