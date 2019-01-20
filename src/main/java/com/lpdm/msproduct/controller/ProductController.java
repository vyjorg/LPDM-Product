package com.lpdm.msproduct.controller;


import com.lpdm.msproduct.dao.ProductDao;
import com.lpdm.msproduct.entity.Category;
import com.lpdm.msproduct.entity.Product;
import com.lpdm.msproduct.entity.Stock;
import com.lpdm.msproduct.proxy.ProducerProxy;
import com.lpdm.msproduct.proxy.StockProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private ProductDao productDao;

    @Autowired
    private StockProxy stockProxy;

    @Autowired
    private ProducerProxy producerProxy;


    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProduct(){
        log.info("ProductController -> méthode listProduct : entrée ");
        List<Product> list = productDao.findAll();
        for(Product product : list){
            log.info("ProductController -> méthode listProduct : boucle ");
            product.setListStock(stockProxy.listStockByProducer(product.getId()));
            product.setProducer(producerProxy.findById(product.getProducerID()));
        }
        log.debug("ProductController -> méthode listProduct : test list vide = "+list.size());
        log.info("ProductController -> méthode listProduct : sortie ");
        return list;
    }

    @GetMapping(value="/products/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product findProduct(@PathVariable int id){
        log.info("ProductController -> méthode findProduct : entrée ");
        log.info("ProductController -> méthode findProduct : id envoyé = "+id);
        Product product = productDao.findById(id);
        product.setListStock(stockProxy.listStockByProducer(product.getId()));
        product.setProducer(producerProxy.findById(product.getProducerID()));
        log.debug("ProductController -> méthode findProduct : test Producer = "+product.getProducer().getName());

        log.info("ProductController -> méthode findProduct : product envoyé = "+product.toString());
        log.info("ProductController -> méthode findProduct : sortie ");
        return product;
    }

    @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addProduct(@RequestBody Product product){
        log.info("ProductController -> méthode addProduct : entrée ");
        log.info("ProductController -> méthode addProduct : product reçu = "+product.toString());
        product.setProducerID(product.getProducer().getId());
        log.debug("ProductController -> méthode findProduct : test ProducerId = "+product.getProducer().getId());
        Product productAdded = productDao.save(product);

        if (productAdded.equals(null)){
            log.debug("ProductController -> méthode findProduct : erreur lors de l'ajout");
        }
        log.info("ProductController -> méthode addProduct : sortie ");
    }

    @DeleteMapping(value="/products/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteProduct(@PathVariable int id){
        log.info("ProductController -> méthode deleteProduct : entrée ");
        log.info("ProductController -> méthode deleteProduct : id envoyé = "+id);
        Product productDelete = productDao.findById(id);
        productDao.deleteById(id);

        if(!productDelete.getListStock().equals(null)){
            for(Stock stockDelete : productDelete.getListStock()){
                stockProxy.deleteStock(stockDelete.getId());
            }
        }
        log.info("ProductController -> méthode deleteProduct : sortie ");
    }

    @PutMapping(value="/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateProduct(@RequestBody Product product){
        log.info("ProductController -> méthode updateProduct : entrée ");
        log.info("ProductController -> méthode updateProduct : product reçu = "+product.toString());

        product.setProducerID(product.getProducer().getId());
        log.debug("ProductController -> méthode updateProduct : test ProducerID = "+product.getProducer().getId());
        productDao.save(product);
/*
        if(!product.getListStock().equals(null)){
            for(Stock stockUpdate : product.getListStock()){
                stockProxy.updateStock(stockUpdate);
            }
        }
        */
        log.info("ProductController -> méthode updateProduct : sortie ");
    }

    @GetMapping(value = "/products/category/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProductByCategoryById(@PathVariable int id){
        log.info("ProductController -> méthode listProductByCategoryById : entrée ");
        log.info("ProductController -> méthode listProductByCategoryById : id envoyé = "+id);
        List<Product> listProducts = productDao.findByCategoryId(id);

        log.debug("ProductController -> méthode listProductByCategoryById : test listProducts = "+listProducts.size());

        for(Product product : listProducts){
            log.info("ProductController -> méthode listProductByCategoryById : boucle ");
            product.setListStock(stockProxy.listStockByProducer(product.getId()));
            product.setProducer(producerProxy.findById(product.getProducerID()));
        }
        log.info("ProductController -> méthode listProductByCategoryById : sortie ");
        return listProducts;
    }

    @PostMapping(value = "/products/category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProductByCategory(@RequestBody Category category){
        log.info("ProductController -> méthode listProductByCategory : entrée ");
        log.info("ProductController -> méthode listProductByCategory : category reçu = "+category.toString());
        List<Product> listProducts = productDao.findByCategoryId(category.getId());

        for(Product product : listProducts){
            log.info("ProductController -> méthode listProductByCategory : boucle ");
            product.setListStock(stockProxy.listStockByProducer(product.getId()));
            product.setProducer(producerProxy.findById(product.getProducerID()));
        }
        log.info("ProductController -> méthode listProductByCategory : sortie ");
        return listProducts;
    }

    @GetMapping(value = "/products/producer/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProductByProducerId(@PathVariable int id){
        log.info("ProductController -> méthode listProductByProducerId : entrée ");
        log.info("ProductController -> méthode listProductByProducerId : id envoyé = "+id);
        List<Product> listProducts = productDao.findByProducerID(id);

        for(Product product : listProducts){
            log.info("ProductController -> méthode listProductByProducerId : boucle ");
            product.setListStock(stockProxy.listStockByProducer(product.getId()));
            product.setProducer(producerProxy.findById(product.getProducerID()));
        }
        log.info("ProductController -> méthode listProductByProducerId : sortie ");
        return listProducts;
    }

    @GetMapping(value = "/products/name/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> listProductByName(@PathVariable String name){
        log.info("ProductController -> méthode listProductByName : entrée ");
        log.info("ProductController -> méthode listProductByName : name envoyé = "+name);
        List<Product> listProducts = productDao.findAllByNameContainingIgnoreCase(name);

        for(Product product : listProducts){
            log.info("ProductController -> méthode listProductByName : entrée ");
            product.setListStock(stockProxy.listStockByProducer(product.getId()));
            product.setProducer(producerProxy.findById(product.getProducerID()));
        }

        log.info("ProductController -> méthode listProductByName : sortie ");
        return listProducts;
    }
}
