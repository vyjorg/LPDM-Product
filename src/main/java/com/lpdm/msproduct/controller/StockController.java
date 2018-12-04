package com.lpdm.msproduct.controller;

import com.lpdm.msproduct.entity.Product;
import com.lpdm.msproduct.entity.Stock;
import com.lpdm.msproduct.proxy.StockProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockProxy stockProxy;

    @GetMapping(value = "/stocks/{id}")
    public Stock showStock(@PathVariable int id){
        Stock stock = stockProxy.findById(id);

        return stock;
    }
}
