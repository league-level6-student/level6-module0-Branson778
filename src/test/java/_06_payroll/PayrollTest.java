package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
        double a = 20.5;
        int b = 6;
        double expected = 123;
        //when
        double actual = payroll.calculatePaycheck(a,b);
        //then
        assertEquals(expected, actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
        int a = 27;
        double expected = 15.525;
        //when
        double actual = payroll.calculateMileageReimbursement(a);
        //15.524999999999999
        //00.000000000000001
        //Adding 0.000000000000001 to our actual value is needed to fix errors with doubles.
        //15.525
        //then
        assertEquals(expected, actual+=0.000000000000001);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
        String a = "Bob";
        double b = 15.75;
        String expected = "Hello Bob, We are pleased to offer you an hourly wage of 15.75";
        //when
        String actual = payroll.createOfferLetter(a,b);
        //then
        assertEquals(expected, actual);
    }

}