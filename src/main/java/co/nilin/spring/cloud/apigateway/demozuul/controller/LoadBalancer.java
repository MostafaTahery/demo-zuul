package co.nilin.spring.cloud.apigateway.demozuul.controller;

import co.nilin.spring.cloud.apigateway.demozuul.service.LoadBalancingProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class LoadBalancer {

    @Autowired
    LoadBalancingProxy loadBalancingProxy;

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/lb")
    public String getService(){
        return this.restTemplate.getForObject("http://myApp/getConfigFromProperty",String.class);
    }

    @GetMapping("/feignlb")
    public String getFeign(){
        return loadBalancingProxy.getConfigFromProperty();
    }

    @GetMapping("/fault-tolerance")
    @HystrixCommand(fallbackMethod = "circuitBreaker")
    public String faultyCall(){
        throw new RuntimeException("Fault detected");
    }

    public String circuitBreaker(){
        return "Circuit Breaker Enabled";
    }
}
