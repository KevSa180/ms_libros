package com.maovares.ms_books;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @GetMapping(path = "/")
    public String index() {
        return "Books MS running";
    }
}
