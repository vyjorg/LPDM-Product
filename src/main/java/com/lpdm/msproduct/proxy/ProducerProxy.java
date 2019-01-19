package com.lpdm.msproduct.proxy;

import com.lpdm.msproduct.entity.Producer;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Component
@FeignClient(name = "zuul-server", url = "https://zuul.lpdm.kybox.fr")
@RibbonClient(name = "microservice-authentication")
public interface ProducerProxy {

    @RequestMapping(path = "microservice-authentication/users/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Producer findById(@PathVariable(value = "id") int id);

    @RequestMapping(path = "microservice-authentication/users/name/{name}",
            method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Producer findByLastName(@PathVariable(value = "name") String name);

    @RequestMapping(path = "microservice-authentication/users/email/{email}",
            method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Producer findByEmail(@PathVariable(value = "email") String name);
}
