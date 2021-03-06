package com.lpdm.msproduct.dao;

import com.lpdm.msproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>  {

    Product findById(int id);

    List<Product> findByCategoryId(int id);

    List<Product> findByCategoryIdAndAndProducerID(int categoryId,int producerId);

    List<Product> findByProducerID(int producerId);

    List<Product> findByName(String name);

    List<Product> findAllByNameContainingIgnoreCase(String name);

}
