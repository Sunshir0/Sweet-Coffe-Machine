package fr.sweetcoffe;

import java.math.BigDecimal;

public class OrderFromCustomer {
    private CoffeMachine.Drinks drink;
    private String sugars;
    private BigDecimal currentAmount;
    private CoffeMachine.Drinks.Option option;


    public OrderFromCustomer(CoffeMachine.Drinks drink) {
        this.drink = drink;
        this.sugars = "";

    }

    public OrderFromCustomer(CoffeMachine.Drinks drink, int sugars) {
        this.drink = drink;
        this.sugars = String.valueOf(sugars);
    }

    public OrderFromCustomer(CoffeMachine.Drinks drink, String sugars, BigDecimal currentAmount) {
        this.drink = drink;
        this.sugars = String.valueOf(sugars);
        this.currentAmount = currentAmount;
    }

    public OrderFromCustomer(int sugars, int stick) {
        this.sugars = String.valueOf(sugars);
    }

    public OrderFromCustomer(CoffeMachine.Drinks drink, String sugars, BigDecimal currentAmount, CoffeMachine.Drinks.Option option) {
        this.drink = drink;
        this.sugars = String.valueOf(sugars);
        this.currentAmount = currentAmount;
        this.option = option;

    }

    public CoffeMachine.Drinks.Option option() {
        return this.option;
    }


    public OrderFromCustomer updateOption(CoffeMachine.Drinks.Option option) {
        return new OrderFromCustomer(drink, sugars, currentAmount, option);
    }

    public OrderFromCustomer changeDrink(CoffeMachine.Drinks drink) {
        return new OrderFromCustomer(drink);
    }

    public OrderFromCustomer addSugar(int sugars, int stick) {
        return new OrderFromCustomer(sugars, stick);
    }

    public String drink() {
        switch (this.drink.toString()) {
            case "T":
                return "T";
            case "H":
                return "H";
            case "C":
                return "C";
        }
        return "";
    }

    public String sugars() {
        return this.sugars;
    }

    public OrderFromCustomer updateCurrentAmount(BigDecimal currentAmount) {
        return new OrderFromCustomer(drink, sugars, currentAmount);
    }

    public String drinkToString() {
        return String.valueOf(drink);
    }

    public BigDecimal currentAmount() {
        return this.currentAmount;
    }

    public String optionToString() {
        return String.valueOf(option);
    }

    public CoffeMachine.Drinks drinkConstant() {
        return this.drink;
    }


}
