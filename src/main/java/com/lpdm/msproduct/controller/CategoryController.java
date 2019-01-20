package com.lpdm.msproduct.controller;

import com.lpdm.msproduct.dao.CategoryDao;
import com.lpdm.msproduct.entity.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Category> listCategories(){
        log.info("CategoryController -> méthode listCategories : entrée ");
        List<Category> listCategory = categoryDao.findAll();
        log.debug("CategoryController -> méthode listCategories : test listCategory = "+listCategory.size());

        log.info("CategoryController -> méthode listCategories : sortie ");
        return listCategory;
    }

    @GetMapping(value = "/categories/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Category category(@PathVariable int id){
        log.info("CategoryController -> méthode category : entrée ");
        Category category = categoryDao.findById(id);
        log.debug("CategoryController -> méthode category : test category = "+category.getId());

        log.info("CategoryController -> méthode category : sortie ");
        return category;
    }

    @PostMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addCategory(@RequestBody Category category){
        log.info("CategoryController -> méthode addCategory : entrée ");
        Category categoryAdded = categoryDao.save(category);

        if(categoryAdded.equals(null)){
            log.debug("CategoryController -> méthode addCategory : Problème lors de l'ajout de la catégorie ");
        }
        log.info("CategoryController -> méthode addCategory : sortie ");
    }

    @DeleteMapping(value="/categories/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteCategory(@PathVariable int id){
        log.info("CategoryController -> méthode deleteCategory : entrée ");
        categoryDao.deleteById(id);
        log.info("CategoryController -> méthode deleteCategory : sortie ");
    }

    @PutMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateCategory(@RequestBody Category category){
        log.info("CategoryController -> méthode updateCategory : entrée ");
        categoryDao.save(category);
        log.info("CategoryController -> méthode updateCategory : sortie ");
    }

}
