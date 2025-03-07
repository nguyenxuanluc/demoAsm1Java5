package com.example.demoasm1java5.Controller;

import com.example.demoasm1java5.Entity.Product;
import com.example.demoasm1java5.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Product> featuredProducts = productService.getFeaturedProducts();
        model.addAttribute("featuredProducts", featuredProducts);
        return "index";
    }
}
