package com.lpdm.msproduct.controller;

import com.lpdm.msproduct.dao.CategoryDao;
import com.lpdm.msproduct.dao.ProductDao;
import com.lpdm.msproduct.entity.Category;
import com.lpdm.msproduct.entity.Product;
import com.lpdm.msproduct.entity.Stock;
import com.lpdm.msproduct.proxy.ProducerProxy;
import com.lpdm.msproduct.proxy.StockProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private StockProxy stockProxy;

    @Autowired
    private ProducerProxy producerProxy;


    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProduct(){
        List<Product> list = productDao.findAll();
        for(Product product : list){
            product.setListStock(stockProxy.listStockByProducer(product.getId()));
            product.setProducer(producerProxy.findById(product.getProducerID()));
        }

        return list;
    }

    @GetMapping(value="/products/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product findProduct(@PathVariable int id){
        Product product = productDao.findById(id);
        product.setListStock(stockProxy.listStockByProducer(product.getId()));
        product.setProducer(producerProxy.findById(product.getProducerID()));

        return product;
    }

    @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addProduct(@RequestBody Product product){
        Product productAdded = productDao.save(product);

        if (productAdded.equals(null)){
            System.out.println("Erreur lors de l'ajout");
        }
    }

    @DeleteMapping(value="/products/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteProduct(@PathVariable int id){
        Product productDelete = productDao.findById(id);
        productDao.deleteById(id);

        if(!productDelete.getListStock().equals(null)){
            for(Stock stockDelete : productDelete.getListStock()){
                stockProxy.deleteStock(stockDelete.getId());
            }
        }
    }

    @PutMapping(value="/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateProduct(@RequestBody Product product){
        productDao.save(product);

        if(!product.getListStock().equals(null)){
            for(Stock stockUpdate : product.getListStock()){
                stockProxy.updateStock(stockUpdate);
            }
        }
    }

    @GetMapping(value = "/products/category/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProductByCategory(@PathVariable int id){
        List<Product> listProducts = productDao.findByCategoryId(id);

        for(Product product : listProducts){
            product.setListStock(stockProxy.listStockByProducer(product.getId()));
            product.setProducer(producerProxy.findById(product.getProducerID()));
        }

        return listProducts;
    }

    @PostMapping(value = "/products/category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProductByCategory2(@RequestBody Category category){
        List<Product> listProducts = productDao.findByCategoryId(category.getId());

        for(Product tempProduct : listProducts){
            System.out.println(tempProduct.toString());
        }

        return listProducts;
    }

    @PostMapping(value = "/products/categoryandproducer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProductByCategoryAndProducer(@RequestBody Category category, int producerId){
        List<Product> listProducts = productDao.findByCategoryIdAndAndProducerID(category.getId(),producerId);

        return listProducts;
    }

    @GetMapping(value = "/products/producer/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProductByProducerId(@PathVariable int id){
        List<Product> listProducts = productDao.findByProducerID(id);

        for(Product product : listProducts){
            product.setListStock(stockProxy.listStockByProducer(product.getId()));
            product.setProducer(producerProxy.findById(product.getProducerID()));
        }

        return listProducts;
    }

    @GetMapping(value = "/products/name/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProductByName(@PathVariable String name){
        List<Product> listProducts = productDao.findByName(name);

        for(Product product : listProducts){
            product.setListStock(stockProxy.listStockByProducer(product.getId()));
            product.setProducer(producerProxy.findById(product.getProducerID()));
        }

        return listProducts;
    }
}
