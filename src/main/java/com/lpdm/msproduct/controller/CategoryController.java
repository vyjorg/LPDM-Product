package com.lpdm.msproduct.controller;

import com.lpdm.msproduct.dao.CategoryDao;
import com.lpdm.msproduct.entity.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vianney
 * @version 1.0
 * @since 07/12/2018
 */

@RestController
public class CategoryController {
    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private CategoryDao categoryDao;

    /**
     * Call this method to get an {@link List<Category>}
     * @return An {@link List<Category>} json object
     */
    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Category> listCategories(){
        log.info("CategoryController -> méthode listCategories : entrée ");
        List<Category> listCategory = categoryDao.findAll();
        log.debug("CategoryController -> méthode listCategories : test listCategory = "+listCategory.size());

        log.info("CategoryController -> méthode listCategories : sortie ");
        return listCategory;
    }


    /**
     * Find {@link Category} by the category {@link Integer} id
     * @param id The {@link Category} {@link Integer} id
     * @return an {@link Category} json object
     */
    @GetMapping(value = "/categories/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Category category(@PathVariable int id){
        log.info("CategoryController -> méthode category : entrée ");
        Category category = categoryDao.findById(id);
        log.debug("CategoryController -> méthode category : test category = "+category.getId());

        log.info("CategoryController -> méthode category : category envoyé = "+category.toString());
        log.info("CategoryController -> méthode category : sortie ");
        return category;
    }


    /**
     * Add {@link Category} in database
     * @param category {@link Category}
     */
    @PostMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void addCategory(@RequestBody Category category){
        log.info("CategoryController -> méthode addCategory : entrée ");
        log.info("CategoryController -> méthode addCategory : category reçu = "+category.toString());
        Category categoryAdded = categoryDao.save(category);

        if(categoryAdded.equals(null)){
            log.debug("CategoryController -> méthode addCategory : Problème lors de l'ajout de la catégorie ");
        }
        log.info("CategoryController -> méthode addCategory : sortie ");
    }

    /**
     * Delete {@link Category} by the category {@link Integer} id
     * @param id The {@link Category} {@link Integer} id
     */
    @DeleteMapping(value="/categories/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteCategory(@PathVariable int id){
        log.info("CategoryController -> méthode deleteCategory : entrée ");
        log.info("CategoryController -> méthode deleteCategory : id reçu = "+id);
        categoryDao.deleteById(id);
        log.info("CategoryController -> méthode deleteCategory : sortie ");
    }

    /**
     * Update {@link Category} in database
     * @param category {@link Category}
     */
    @PutMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateCategory(@RequestBody Category category){
        log.info("CategoryController -> méthode updateCategory : entrée ");
        log.info("CategoryController -> méthode updateCategory : category reçu = "+category.toString());
        categoryDao.save(category);
        log.info("CategoryController -> méthode updateCategory : sortie ");
    }

}
