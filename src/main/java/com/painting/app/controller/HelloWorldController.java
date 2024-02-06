package com.painting.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
     @GetMapping("/test")
     public String saveEmployee() {
          return "hello world";
     }
}
