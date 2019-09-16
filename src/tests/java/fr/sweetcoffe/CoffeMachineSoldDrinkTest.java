package fr.sweetcoffe;

import fr.sweetcoffe.CoffeMachine;
import fr.sweetcoffe.OrderFromCustomer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CoffeMachineTest {

    @InjectMocks
    private CoffeMachine coffeeMachine = new CoffeMachine();
    @Mock
    private OrderFromCustomer orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.T);

    @Test
    public void resultCForCoffe() {
        assertThat(coffeeMachine.selectDrink("C").equals("C")).isTrue();
    }

    @Test
    public void resultHForChocolate() {
        assertThat(coffeeMachine.selectDrink("H").equals("H")).isTrue();
    }

    @Test
    public void resultTForTea() {
        assertThat(coffeeMachine.selectDrink("T").equals("T")).isTrue();
    }

    @Test
    public void shouldSendErrorMessageForIncorrectInstructionsForDrinks() {
        assertEquals("Error : Choose between TEA:T, CAFFE:C, CHOCOLATE:H", coffeeMachine.selectDrink(""));
    }

    @Test
    public void shouldReturn1ToAdd1sugar() {
        assertEquals("1", coffeeMachine.addSugar("1"));
    }

    @Test
    public void NonToAdd1sugar() {
        assertThat(coffeeMachine.addSugar("1").equals("0")).isFalse();
    }

    @Test
    public void shouldReturn2ToAdd2sugars() {
        assertEquals("2", coffeeMachine.addSugar("2"));
    }

    @Test
    public void NonToAdd2sugar() {
        assertThat(coffeeMachine.addSugar("2").equals("0")).isFalse();
    }

    @Test
    public void resultTForTeaWithoutSugar() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.T);
        assertEquals("T::", coffeeMachine.sendInstructionsToDrinkMaker(orderFromCustomerMock));
    }

    @Test
    public void resultHForChocolateWithNoSugarAndNoStick() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.H);
        assertEquals("H::", coffeeMachine.sendInstructionsToDrinkMaker(orderFromCustomerMock));
    }

    @Test
    public void resultCforCoffeWith2Sugars() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.C, 1);
        assertEquals("C:1:0", coffeeMachine.sendInstructionsToDrinkMaker(orderFromCustomerMock));
    }

    @Test
    public void resultCforCoffeWith2SugarsAndStick() {
        orderFromCustomerMock = new OrderFromCustomer(CoffeMachine.Drinks.C, 2);
        assertEquals("C:2:0", coffeeMachine.sendInstructionsToDrinkMaker(orderFromCustomerMock));
    }


    @Test
    public void shouldReturnMAndMessageContentWhenSendingMessageToTheCoffeeMachineInterface() {
        assertEquals("M:Hello, please select your drink", coffeeMachine.screenDisplay("Hello, please select your drink"));
    }

}
