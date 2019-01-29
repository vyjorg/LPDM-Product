package com.lpdm.msproduct.Controller;

import com.lpdm.msproduct.controller.ProductController;
import com.lpdm.msproduct.entity.Product;
import com.lpdm.msproduct.utils.ObjToJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductController productController;

    private Product product;
    private List<Product> productList;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        product=new Product();
        product.setId(1);
        product.setName("name");
        product.setLabel("label");
        product.setPicture("picture");
        product.setPrice(10);
        product.setTax(10);
        product.setDeactivate(false);

        productList = new ArrayList<Product>();
        productList.add(product);
        productList.add(product);
    }

    @Test
    public void findProductByIdTest() throws Exception {
        Mockito.when(productController.findProduct(1)).thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(ObjToJson.get(product)));

        Mockito.verify(productController, Mockito.times(1)).findProduct(1);
        Mockito.verifyNoMoreInteractions(productController);
    }

    @Test
    public void findProductByNameTest() throws Exception {
        Mockito.when(productController.listProductByName("name")).thenReturn(productList);

        mockMvc.perform(get("/products/name/name"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(ObjToJson.get(productList)));

        Mockito.verify(productController, Mockito.times(1)).listProductByName("name");
        Mockito.verifyNoMoreInteractions(productController);
    }

    @Test
    public void listProductTest() throws Exception{
        Mockito.when(productController.listProduct()).thenReturn(productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(ObjToJson.get(productList)));

        Mockito.verify(productController, Mockito.times(1)).listProduct();
        Mockito.verifyNoMoreInteractions(productController);

    }

    @Test
    public void deleteProductTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/products/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjToJson.get(product));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
    }
}
