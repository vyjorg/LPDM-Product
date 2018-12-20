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
    private StockProxy stockProxy;


    @GetMapping(value = "/products")
    public List<Product> listProduct(){
        List<Product> list = productDao.findAll();
        for(Product product : list){
            product.setListStock(stockProxy.listStockByProductor(product.getId()));
        }

        return list;
    }

    @GetMapping(value="/products/{id}")
    public Product findProduct(@PathVariable int id){
        Product product = productDao.findById(id);
        product.setListStock(stockProxy.listStockByProductor(product.getId()));

        return product;
    }

    @PostMapping(value = "/products")
    public void addProduct(@RequestBody Product product){
        Product productAdded = productDao.save(product);

        if (productAdded.equals(null)){
            System.out.println("Erreur lors de l'ajout");
        }
    }

    @DeleteMapping(value="/products/{id}")
    public void deleteProduct(@PathVariable int id){
        Product productDelete = productDao.findById(id);
        productDao.deleteById(id);

        if(!productDelete.getListStock().equals(null)){
            for(Stock stockDelete : productDelete.getListStock()){
                stockProxy.deleteStock(stockDelete.getId());
            }
        }
    }

    @PutMapping(value="/products")
    public void updateProduct(@RequestBody Product product){
        productDao.save(product);

        if(!product.getListStock().equals(null)){
            for(Stock stockUpdate : product.getListStock()){
                stockProxy.updateStock(stockUpdate);
            }
        }
    }

    @GetMapping(value = "/products/category/{id}")
    public List<Product> listProductByCategory(@PathVariable int id){
        List<Product> listProducts = productDao.findByCategoryId(id);

        for(Product tempProduct : listProducts){
            System.out.println(tempProduct.toString());
        }

        return listProducts;
    }

    @PostMapping(value = "/products/category")
    public List<Product> listProductByCategory2(@RequestBody Category category){
        List<Product> listProducts = productDao.findByCategoryId(category.getId());

        for(Product tempProduct : listProducts){
            System.out.println(tempProduct.toString());
        }

        return listProducts;
    }

    @PostMapping(value = "/products/categoryandproductor")
    public List<Product> listProductByCategoryAndProductor(@RequestBody Category category, int productorId){
        List<Product> listProducts = productDao.findByCategoryIdAndAndProductorID(category.getId(),productorId);

        return listProducts;
    }
}
