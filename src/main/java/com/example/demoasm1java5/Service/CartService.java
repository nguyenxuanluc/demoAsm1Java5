package com.example.demoasm1java5.Service;

import com.example.demoasm1java5.Entity.Cart;
import com.example.demoasm1java5.Entity.Order;
import com.example.demoasm1java5.Entity.OrderDetail;
import com.example.demoasm1java5.Entity.Product;
import com.example.demoasm1java5.Repository.CartRepository;
import com.example.demoasm1java5.Repository.OrderDetailRepository;
import com.example.demoasm1java5.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    public CartService(CartRepository cartRepository, OrderDetailRepository orderDetailRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
    }

    private final List<Cart> cartItems = new ArrayList<>();

    public List<Cart> addToCart(Product product) {
        boolean exists = false;
        for (Cart item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                exists = true;
                break;
            }
        }
        if (!exists) {
            Cart newItem = new Cart();
            newItem.setProduct(product);
            newItem.setQuantity(1);
            cartItems.add(newItem);
        }
        return cartItems;
    }

    public void updateCart(Integer cartId, Integer quantity) {
        for (Cart item : cartItems) {
            if (item.getId().equals(cartId)) {
                item.setQuantity(quantity);
                return;
            }
        }
        throw new RuntimeException("Không tìm thấy sản phẩm trong giỏ hàng");
    }


//    public void removeFromCart(Integer cartItemId) {
//        Optional<Cart> cartOpt = cartRepository.findById(cartItemId);
//        if (cartOpt.isPresent()) {
//            Cart cartItem = cartOpt.get();
//            if (cartItem.getQuantity() > 1) {
//                cartItem.setQuantity(cartItem.getQuantity() - 1);
//                cartRepository.save(cartItem);
//            } else {
//                cartRepository.delete(cartItem);
//            }
//        }
//    }
public void removeFromCart(Integer id) {
    cartRepository.deleteById(id);
}




    public List<Cart> getCart() {
        return new ArrayList<>(cartItems);
    }

    public double calculateTotal(List<Cart> cartItems) {
        return cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void checkout() {
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống");
        }

        Order order = new Order();
        order.setTotalPrice(calculateTotal(cartItems));
        order.setStatus("Pending");
        orderRepository.save(order);

        for (Cart cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
            orderDetailRepository.save(orderDetail);
        }

        clearCart();
    }
}
