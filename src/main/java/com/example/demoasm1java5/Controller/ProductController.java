package com.example.demoasm1java5.Controller;

import com.example.demoasm1java5.Entity.Product;
import com.example.demoasm1java5.Repository.CategoryRepository;
import com.example.demoasm1java5.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productService.getAllProducts(pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());

        return "products";
    }

    @GetMapping("/filter")
    public String filterProducts(@RequestParam(required = false) Double minPrice,
                                 @RequestParam(required = false) Double maxPrice,
                                 Model model) {
        if (minPrice == null) minPrice = 0.0;
        if (maxPrice == null) maxPrice = Double.MAX_VALUE;

        List<Product> filteredProducts = productService.filterByPrice(minPrice, maxPrice);
        model.addAttribute("products", filteredProducts);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

        return "products";  // Giao diện hiển thị danh sách sản phẩm
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Integer id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product_detail";
        }
        return "redirect:/products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add_product";
    }

    @PostMapping("/add-product")
    public String saveProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "add_product";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> searchResults = productService.searchByName(keyword);
        model.addAttribute("products", searchResults);
        model.addAttribute("keyword", keyword);
        return "product-list";
    }
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "edit_product";
        }
        return "redirect:/products";
    }
    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "edit_product";
        }
        productService.updateProduct(product);
        return "redirect:/products";
    }
}
