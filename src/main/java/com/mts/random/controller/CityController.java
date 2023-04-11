package com.mts.random.controller;

import com.mts.random.service.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CityController {
    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private RandomGenerator randomGenerator;

    // for simplicity's sake, this method is get, else would be post :)
    // this method serves purpose of manual testing of publishing message
    @GetMapping("/pub")
    public String publish() throws IOException {
        String city = this.randomGenerator.getRandomApi();

        String result = this.randomGenerator.publishMessage();

        return result;
    }
}
