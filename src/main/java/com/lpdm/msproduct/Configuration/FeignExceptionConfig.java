package com.lpdm.msproduct.Configuration;

import com.lpdm.msproduct.exception.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignExceptionConfig  {
    @Bean
    public CustomErrorDecoder mCustomErrorDecoder(){
        return new CustomErrorDecoder();
    }
}
