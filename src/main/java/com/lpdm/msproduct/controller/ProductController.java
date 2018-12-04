package com.lpdm.msproduct.controller;

import com.lpdm.msproduct.dao.ProductDao;
import com.lpdm.msproduct.entity.Product;
import com.lpdm.msproduct.proxy.StockProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private StockProxy stockProxy;


    @GetMapping(value = "/products")
    public List<Product> listProduct(){
        List<Product> list = productDao.findAll();
        for(Product product : list){
            product.setStock(stockProxy.findById(product.getStockId()));
        }

        return list;
    }

    @GetMapping(value="/products/{id}")
    public Product findProduct(@PathVariable int id){
        Product product = productDao.findById(id);
        product.setStock(stockProxy.findById(product.getStockId()));

        return product;
    }
}
