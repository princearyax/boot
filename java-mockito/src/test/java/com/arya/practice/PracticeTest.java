package com.arya.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PracticeTest {

    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private OrderService orderService;

    @Test
    void processPaymentGreater5000(){
        String id = "meow";
        double amt = 50001.9;

        when(paymentGateway.processPayment(anyString(), anyDouble())).thenReturn(true);

        String res1 = orderService.placeOrder(id, amt);

        assertEquals("Placed", res1);

        verify(paymentGateway, times(1)).processPayment(id, amt);
    }

    @Test
    void processPaymentLess5000(){
        String id = "meow";
        double amt = 456.9;

        String res2 = orderService.placeOrder(id, amt);

        assertEquals("Placed", res2);

       verify(paymentGateway, never()).processPayment(id, amt);
    }
}
