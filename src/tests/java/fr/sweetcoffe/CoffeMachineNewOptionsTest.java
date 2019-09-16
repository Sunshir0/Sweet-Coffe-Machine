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
public class CoffeMachineNewOptionsTest {

    @InjectMocks
    private CoffeMachine coffeeMachine = new CoffeMachine();
    @Mock
    private OrderFromCustomer orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.T);

    @Test
    public void shouldReturnOAndColonAndColonWhenGivenOrderWith60Cents() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.O);
        orderFromCustomerMock = orderFromCustomerMock.updateCurrentAmount(new BigDecimal("0.6"));
        assertEquals("O::", coffeeMachine.sendToDrinkMaker(orderFromCustomerMock));
    }

    @Test
    public void shouldReturnChAndColonAndColonWhenGivenOrderWith1Euro() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.C);
        orderFromCustomerMock = orderFromCustomerMock.updateCurrentAmount(new BigDecimal("1"));
        orderFromCustomerMock = orderFromCustomerMock.updateOption(CoffeMachine.Drinks.Option.EH);
        assertEquals("CEH::", coffeeMachine.sendToDrinkMaker(orderFromCustomerMock));
    }

    @Test
    public void shouldReturnHhAndColonAnd1AndColonAnd0WhenGivenOrderWith1Euro() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.H, 1);
        orderFromCustomerMock = orderFromCustomerMock.updateCurrentAmount(new BigDecimal("1"));
        orderFromCustomerMock = orderFromCustomerMock.updateOption(CoffeMachine.Drinks.Option.EH);
        assertEquals("HEH:1:0", coffeeMachine.sendToDrinkMaker(orderFromCustomerMock));
    }

    @Test
    public void shouldReturnThAndColonAnd2AndColonAnd0WhenGivenOrderWith1Euro() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.T, 2);
        orderFromCustomerMock = orderFromCustomerMock.updateCurrentAmount(new BigDecimal("1"));
        orderFromCustomerMock = orderFromCustomerMock.updateOption(CoffeMachine.Drinks.Option.EH);
        assertEquals("TEH:2:0", coffeeMachine.sendToDrinkMaker(orderFromCustomerMock));
    }


}
