package com.jcathcart.bookstore.controllers;

import com.jcathcart.bookstore.constants.AppConstants;
import com.jcathcart.bookstore.util.MemoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final Logger log = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private MemoryUtil memoryUtil;

    @GetMapping(AppConstants.HEALTH_CHECK_URL)
    public String healthCheck() {
        return "Book store service is up!";
    }

    @GetMapping(AppConstants.MEMORY_USAGE_URL)
    public String memoryCheck() {
        return memoryUtil.getStats();
    }

}
