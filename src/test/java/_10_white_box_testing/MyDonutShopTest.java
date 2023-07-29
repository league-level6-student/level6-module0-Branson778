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

    myDonutShop = new MyDonutShop(paymentService,deliveryService,bakeryService);
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
myDonutShop.takeOrder(order);
//when(*Order Complete*).thenReturn(true);
        //when
//verify(*Order Completer*, times(1)).*CompletionMethod*(order);
        //then
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given

        //when

        //then
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given

        //when

        //then
    }

}