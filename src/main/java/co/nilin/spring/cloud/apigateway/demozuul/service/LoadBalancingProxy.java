package co.nilin.spring.cloud.apigateway.demozuul.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "config-service",url = "localhost:8082")
public interface LoadBalancingProxy {

    @GetMapping("/getConfigFromProperty")
    public String getConfigFromProperty();

}
