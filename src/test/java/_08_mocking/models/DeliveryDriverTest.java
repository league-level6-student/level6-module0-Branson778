package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;

    @Mock
    CellPhone cellPhone;

    @Mock
    Car car;

    String name = "Bob";

    @BeforeEach
    void setUp() {
   MockitoAnnotations.openMocks(this);

        deliveryDriver = new DeliveryDriver(name,car,cellPhone);
    }

    @Test
    void itShouldWasteTime() {
        //given
       boolean expected = true;

        //when
        when(cellPhone.browseCatMemes()).thenReturn(true);
        //then
        boolean actual = deliveryDriver.wasteTime();

        assertEquals(expected, actual);
    }

    @Test
    void itShouldRefuel() {
        //given
        int a = 12;
        boolean expected = true;
        //when
       when(car.fillTank(a)).thenReturn(true);
       boolean actual = deliveryDriver.refuel(a);
        //then
        assertEquals(expected,actual);
    }

    @Test
    void itShouldContactCustomer() {
        //given
       String a = "222-333-4455";
       boolean expected = true;
        //when
       when(cellPhone.call(a)).thenReturn(true);
       boolean actual = deliveryDriver.contactCustomer(a);
        //then
        assertEquals(expected,actual);
    }

}