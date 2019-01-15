package com.lpdm.msproduct.controller;

import com.lpdm.msproduct.entity.Producer;
import com.lpdm.msproduct.proxy.ProducerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProducerController {

    @Autowired
    private ProducerProxy producerProxy;

    @GetMapping(value="/producer/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Optional<Producer> findProducer(@PathVariable int id){
        Optional<Producer> producer = producerProxy.findById(id);
        return producer;
    }
}
