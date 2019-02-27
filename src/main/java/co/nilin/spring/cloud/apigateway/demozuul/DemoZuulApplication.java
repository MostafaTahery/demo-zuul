package co.nilin.spring.cloud.apigateway.demozuul;

import co.nilin.spring.cloud.apigateway.demozuul.controller.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableFeignClients
@EnableHystrix
@EnableCircuitBreaker
@Configuration
@EnableHystrixDashboard
public class DemoZuulApplication {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    LoadBalancer loadBalancer;

    @GetMapping("to-get")
    public String toGet(){
        return loadBalancer.getFeign();
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoZuulApplication.class, args);
	}

	@GetMapping("/my-health-check")
	public ResponseEntity<String> myCustomCheck(){
		String message="Testing My Health Function";
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
