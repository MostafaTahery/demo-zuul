package co.nilin.spring.cloud.apigateway.demozuul.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "myApp" )
public interface LoadBalancingProxy {

    @GetMapping("/getConfigFromProperty")
    public String getConfigFromProperty();

}
