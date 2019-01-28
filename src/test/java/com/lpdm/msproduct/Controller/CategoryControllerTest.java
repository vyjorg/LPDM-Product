package com.lpdm.msproduct.Controller;

import com.lpdm.msproduct.controller.CategoryController;
import com.lpdm.msproduct.entity.Category;
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
public class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryController categoryController;

    private Category category;
    private List<Category> categoryList;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();

       category = new Category();
       category.setId(1);
       category.setName("name");

       categoryList = new ArrayList<Category>();
       categoryList.add(category);
       categoryList.add(category);
    }

    @Test
    public void categoryTest() throws Exception{
        Mockito.when(categoryController.category(1)).thenReturn(category);

        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(ObjToJson.get(category)));

        Mockito.verify(categoryController, Mockito.times(1)).category(1);
        Mockito.verifyNoMoreInteractions(categoryController);

    }

    @Test
    public void listCategoryTest() throws Exception{
        Mockito.when(categoryController.listCategories()).thenReturn(categoryList);

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(ObjToJson.get(categoryList)));

        Mockito.verify(categoryController, Mockito.times(1)).listCategories();
        Mockito.verifyNoMoreInteractions(categoryController);

    }

    @Test
    public void deleteCategoryTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/categories/1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(ObjToJson.get(category));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print());
    }
}
