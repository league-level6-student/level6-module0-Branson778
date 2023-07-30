package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

    MyDonutShop myDonutShop;

    @Mock
    PaymentService paymentService;

    @Mock
    DeliveryService deliveryService;

    @Mock
    BakeryService bakeryService;

    @BeforeEach
    void setUp() {
    MockitoAnnotations.openMocks(this);
    //bakeryService.makeDonuts();
    myDonutShop = new MyDonutShop(paymentService,deliveryService,bakeryService);
        myDonutShop.openForTheDay();
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
Order order = new Order("CUSTOMER_NAME",
        "CUSTOMER_PHONE_NUMBER",
        1,
        5.00,
        "CREDIT_CARD_NUMBER",
        true);
when(bakeryService.getDonutsRemaining()).thenReturn(50);
when(paymentService.charge(order)).thenReturn(true);
        myDonutShop.takeOrder(order);
        //when
verify(paymentService, times(1)).charge(order);
        //then
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
        Order order = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        //when
        when(bakeryService.getDonutsRemaining()).thenReturn(0);
        Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
        assertEquals(exceptionThrown.getMessage(),"Insufficient donuts remaining");
        try {
            verify(myDonutShop, never()).takeOrder(any());
        }catch(Exception e){}
                //then
        //assertThrows();
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given
myDonutShop.closeForTheDay();
        Order order = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        //when
        Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
        assertEquals(exceptionThrown.getMessage(),"Sorry we're currently closed");
        try {
            verify(myDonutShop, never()).takeOrder(any());
        }catch(Exception e){}
        //then
    }

}