package com.diegojacober.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakController.class);

    @GetMapping("/sample-api")
    // @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    // https://resilience4j.readme.io/docs/circuitbreaker
    @RateLimiter(name = "default")
    // 10s => only 10.000 calls
    @Bulkhead(name = "sample-api")
    public String sampleApi() {

        logger.info("Sample Api call received");

        //ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost/some-dummy-url", String.class);

        //return forEntity.getBody();
        return "Sample-api";
    }

    public String hardcodedResponse(Exception ex) {
        // posso utilizar qualquer tipo de exception para qualquer tipo
        return "fallback-response";
    }
}
