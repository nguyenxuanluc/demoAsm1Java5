package com.example.demoasm1java5.Controller;

import com.example.demoasm1java5.Entity.Cart;
import com.example.demoasm1java5.Entity.Order;
import com.example.demoasm1java5.Entity.OrderDetail;
import com.example.demoasm1java5.Service.CartService;
import com.example.demoasm1java5.Service.OrderDetailService;
import com.example.demoasm1java5.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("")
    public String checkoutPage(Model model) {
        // Lấy giỏ hàng từ dịch vụ, không cần userId
        List<Cart> cartItems = cartService.getCart();
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        double totalPrice = cartService.calculateTotal(cartItems);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        return "checkout";
    }

    @PostMapping("/process")
    public String processCheckout(@RequestParam String name,
                                  @RequestParam String address,
                                  @RequestParam String phone) {
        List<Cart> cartItems = cartService.getCart();
        if (cartItems.isEmpty()) {
            return "redirect:/cart";
        }

        Order order = new Order();
        order.setTotalPrice(cartService.calculateTotal(cartItems));
        order.setStatus("Pending");
        orderService.saveOrder(order);

        for (Cart cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
            orderDetailService.saveOrderDetail(orderDetail);
        }

        cartService.clearCart();

        return "redirect:/checkout/success";
    }

    @GetMapping("/success")
    public String checkoutSuccess() {
        return "checkout_success";
    }
}
