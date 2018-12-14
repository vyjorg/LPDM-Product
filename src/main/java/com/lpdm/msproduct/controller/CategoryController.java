package com.lpdm.msproduct.controller;

import com.lpdm.msproduct.dao.CategoryDao;
import com.lpdm.msproduct.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping(value = "/categories")
    public List<Category> listCategories(){
        List<Category> listCategory = categoryDao.findAll();

        return listCategory;
    }

    @GetMapping(value = "/categories/{id}")
    public Category category(@PathVariable int id){
        Category category = categoryDao.findById(id);

        return category;
    }

    @PostMapping(value = "/categories")
    public void addCategory(@RequestBody Category category){
        Category categoryAdded = categoryDao.save(category);

        if(categoryAdded.equals(null)){
            System.out.println("Problème lors de l'ajout de la catégorie");
        }
    }

    @DeleteMapping(value="/categories/{id}")
    public void deleteCategory(@PathVariable int id){
        categoryDao.deleteById(id);
    }

    @PutMapping(value = "/categories")
    public void updateCategory(@RequestBody Category category){
        categoryDao.save(category);
    }


}
