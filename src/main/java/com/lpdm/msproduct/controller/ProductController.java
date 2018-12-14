package com.lpdm.msproduct.controller;

import com.lpdm.msproduct.dao.CategoryDao;
import com.lpdm.msproduct.dao.ProductDao;
import com.lpdm.msproduct.entity.Category;
import com.lpdm.msproduct.entity.Product;
import com.lpdm.msproduct.entity.Stock;
import com.lpdm.msproduct.proxy.StockProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

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

    @PostMapping(value = "/products")
    public void addProduct(@RequestBody Product product){

        Stock stockAdded= stockProxy.addStock(product.getStock());
        product.setStockId(stockAdded.getId());

        Product productAdded = productDao.save(product);

        if (productAdded.equals(null)){
            System.out.println("Erreur lors de l'ajout");
        }
    }

    @DeleteMapping(value="/products/{id}")
    public void deleteStock(@PathVariable int id){
        Product productDelete = productDao.findById(id);
        productDao.deleteById(id);
        stockProxy.deleteStock(productDelete.getStockId());
    }

    @PutMapping(value="/products")
    public void updateStock(@RequestBody Product product){
        product.setStockId(product.getStock().getId());
        productDao.save(product);
        stockProxy.updateStock(product.getStock());
    }

    @GetMapping(value = "/products/category/{id}")
    public List<Product> listProductByCategory(@PathVariable int id){
        List<Product> listProducts = productDao.findByCategory_Id(id);

        for(Product tempProduct : listProducts){
            System.out.println(tempProduct.toString());
        }

        return listProducts;
    }

    @PostMapping(value = "/products/category")
    public List<Product> listProductByCategory2(@RequestBody Category category){
        List<Product> listProducts = productDao.findByCategory_Id(category.getId());

        for(Product tempProduct : listProducts){
            System.out.println(tempProduct.toString());
        }

        return listProducts;
    }

}
