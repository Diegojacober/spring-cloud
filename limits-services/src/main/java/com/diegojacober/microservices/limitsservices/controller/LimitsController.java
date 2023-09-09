package com.diegojacober.microservices.limitsservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diegojacober.microservices.limitsservices.configuration.Configuration;
import com.diegojacober.microservices.limitsservices.entities.Limits;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retriveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
        // return new Limits(1, 1000);
    }
}
