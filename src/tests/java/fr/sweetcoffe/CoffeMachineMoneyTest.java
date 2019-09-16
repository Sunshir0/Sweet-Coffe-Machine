package fr.sweetcoffe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CoffeMachineMoneyTest {

    @InjectMocks
    private CoffeMachine coffeeMachine = new CoffeMachine();
    @Mock
    private OrderFromCustomer orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.T);

    @Test
    public void shouldReturnMAndMessageContentWhenSendingMessageToTheCoffeeMachineInterface() {
        assertEquals("M:Hello, please select your drink", coffeeMachine.screenDisplay("Hello, please select your drink"));
        assertEquals("M:cents to complete order", coffeeMachine.screenDisplay("cents to complete order"));
    }


    @Test
    public void shouldReturnTrueWhenOrderIsGivenTheCorrectAmountForATea() {
        assertEquals(true, coffeeMachine.isGivenAmountEnough("T", new BigDecimal("0.4")));
    }

    @Test
    public void shouldReturnTrueWhenOrderIsGivenTheCorrectAmountForAChocolate() {
        assertEquals(true, coffeeMachine.isGivenAmountEnough("H", new BigDecimal("0.5")));
    }

    @Test
    public void shouldReturnTrueWhenOrderIsGivenTheCorrectAmountForACoffee() {
        assertEquals(true, coffeeMachine.isGivenAmountEnough("C", new BigDecimal("0.6")));
    }

    @Test
    public void shouldReturnTAndColonAnd1AndColonAnd0WhenGivenOrderWith40Cents() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.T, 1);
        orderFromCustomerMock = orderFromCustomerMock.updateCurrentAmount(new BigDecimal("0.4"));
        assertEquals("T:1:0", coffeeMachine.sendToDrinkMaker(orderFromCustomerMock));
    }

    @Test
    public void shouldReturnCAndColonAnd2AndColonAnd0WhenGivenOrderWith1Euro() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.C, 2);
        orderFromCustomerMock = orderFromCustomerMock.updateCurrentAmount(new BigDecimal("1"));
        assertEquals("C:2:0", coffeeMachine.sendToDrinkMaker(orderFromCustomerMock));
    }

    @Test
    public void shouldReturnHAndColonAndColonWhenGivenOrderWith40Cents() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.H);
        orderFromCustomerMock = orderFromCustomerMock.updateCurrentAmount(new BigDecimal("0.4"));
        assertEquals("M:Missing 10 cents to complete order", coffeeMachine.sendToDrinkMaker(orderFromCustomerMock));
    }


}
