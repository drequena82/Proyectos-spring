package io.spring.billing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.spring.billing.entities.Product;
import io.spring.billing.managers.ProductManager;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductManager productManager;

    @GetMapping(value = "/charge/{term}", produces = { "application/json" })
    public @ResponseBody
    List<Product> chargeProducts(@PathVariable String term) {
        return productManager.findByName(term);
    }

}