package com.lpdm.msproduct.dao;

import com.lpdm.msproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>  {

    Product findById(int id);

}
