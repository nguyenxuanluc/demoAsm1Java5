package com.example.demoasm1java5.Service;

import com.example.demoasm1java5.Entity.Checkout;
import com.example.demoasm1java5.Repository.CheckoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {

    private final CheckoutRepository checkoutRepository;

    public CheckoutService(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }

    public List<Checkout> getAllCheckouts() {
        return checkoutRepository.findAll();
    }

    public Checkout getCheckoutById(Integer id) {
        Optional<Checkout> checkout = checkoutRepository.findById(id);
        return checkout.orElse(null);
    }

    public Checkout createCheckout(Checkout checkout) {
        return checkoutRepository.save(checkout);
    }

    public Checkout updateCheckoutStatus(Integer id, String status) {
        Optional<Checkout> optionalCheckout = checkoutRepository.findById(id);
        if (optionalCheckout.isPresent()) {
            Checkout checkout = optionalCheckout.get();
            checkout.setStatus(status);
            return checkoutRepository.save(checkout);
        }
        return null;
    }

    public void deleteCheckout(Integer id) {
        checkoutRepository.deleteById(id);
    }
}
