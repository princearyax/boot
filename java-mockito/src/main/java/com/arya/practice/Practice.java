package com.arya.practice;

interface PaymentGateway {
    boolean processPayment(String orderID, double amount);
}

class OrderService {
    private PaymentGateway paymentGateway;

    public OrderService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

    public String placeOrder(String orderID, double amount){
        if(amount > 5000){
            if(paymentGateway.processPayment(orderID, amount) == true){
                return "Placed";
            }
            return "Failed";
        }
        return "Placed";
    }
}
