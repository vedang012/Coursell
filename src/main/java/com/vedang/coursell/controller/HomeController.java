package com.vedang.coursell.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hi")
    public String sayHi() {
        return "hi";
    }

}
