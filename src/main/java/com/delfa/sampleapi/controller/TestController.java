package com.delfa.sampleapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping(path = "/api",
            method = RequestMethod.GET)
    public ResponseEntity<?> getTestMessage() {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
