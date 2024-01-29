package com.example.java4.bean;


import com.example.java4.dto.mau_sac.StoreRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean("black")
    public StoreRequest configBean1(){
        StoreRequest r1 = new StoreRequest(1,"ms1","black",1);
        return r1;
    }
    @Bean("white")
    public StoreRequest configBean2(){
        StoreRequest r2 = new StoreRequest(2,"ms2","white",2);
        return r2;
    }
}
