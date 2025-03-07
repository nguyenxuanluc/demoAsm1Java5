package com.example.demoasm1java5.Controller;

import com.example.demoasm1java5.Entity.Cart;
import com.example.demoasm1java5.Entity.Product;
import com.example.demoasm1java5.Service.CartService;
import com.example.demoasm1java5.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewCart(Model model) {
        List<Cart> cartItems = cartService.getCart();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", cartService.calculateTotal(cartItems));
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Integer productId) {
        Optional<Product> productOpt = productService.getProductById(productId);
        productOpt.ifPresent(cartService::addToCart);
        return "redirect:/cart";
    }

    @PostMapping("/update/{cartId}")
    public String updateCart(@PathVariable("cartId") Integer cartId, @RequestParam("quantity") Integer quantity) {
        cartService.updateCart(cartId, quantity);
        return "redirect:/cart";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCartItem(@PathVariable Integer id) {
        try {
            cartService.removeFromCart(id);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }




    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}
