package com.sunny.lab.ct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.lab.ct.service.CategoryService;
import com.commercetools.api.models.category.Category;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
	CategoryService categoryService;

    @GetMapping("/id/{id}")
    public Mono<Category> getCustomerById(@PathVariable String id) {
        return Mono.fromFuture(customerService.getCustomerById(id));
    }

    @GetMapping("/find/{email}")
    public Mono<Category> getCustomerByEmail(@PathVariable String email) {
        return Mono.fromFuture(customerService.getCustomerByEmail(email));
    }
}
