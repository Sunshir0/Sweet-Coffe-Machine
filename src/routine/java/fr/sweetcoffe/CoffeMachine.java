package fr.sweetcoffe;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class CoffeMachine {

    public String selectDrink(@NotNull String drink) {
        String result = "";

        if (!drink.equalsIgnoreCase(Drinks.T.toString())
                && !drink.equalsIgnoreCase(Drinks.H.toString())
                && !drink.equalsIgnoreCase(Drinks.C.toString())
                && !drink.equalsIgnoreCase(Drinks.O.toString()))

            result = "Error : Choose between TEA:T, CAFFE:C, CHOCOLATE:H";
        else
            result = drink;

        return result;
    }

    public String addSugar(@NotNull String numberOfSugars) {
        if (numberOfSugars.equals("1") || numberOfSugars.equals("2"))
            return numberOfSugars;
        return "";
    }

    @NotNull
    public String sendInstructionsToDrinkMaker(@NotNull OrderFromCustomer orderFromCustomer) {
        String instructions = "";
        if (orderFromCustomer.drink().equals(Drinks.T.toString())
                || orderFromCustomer.drink().equals(Drinks.H.toString())
                || orderFromCustomer.drink().equals(Drinks.C.toString())
                || orderFromCustomer.drink().equals(Drinks.O.toString()))

            instructions += selectDrink(orderFromCustomer.drink());
        if (orderFromCustomer.optionToString().equals(Drinks.Option.EH.toString()))
            instructions += "EH";

        instructions += ":";
        if (orderFromCustomer.sugars().isEmpty())
            instructions += ":";
        else
            instructions += addSugar(orderFromCustomer.sugars()) + ":0";
        return instructions;
    }

    public String screenDisplay(String messageContent) {
        return "M:" + messageContent;
    }


    public Boolean isGivenAmountEnough(String selectedDrink, BigDecimal givenAmountFromCustomer) {
        Boolean result = false;
        if (selectedDrink != null) {
            result = isGivenAmountCorrespondingToSelectedDrink(selectedDrink, givenAmountFromCustomer);
        }
        ;
        return result;
    }

    @NotNull
    private Boolean isGivenAmountCorrespondingToSelectedDrink(String selectedDrink, BigDecimal givenAmountFromCustomer) {
        Boolean result = false;
        if (selectedDrink.equalsIgnoreCase(Drinks.T.getInitial()) && (givenAmountFromCustomer.compareTo(Drinks.T.getMoney()) == 0 || givenAmountFromCustomer.compareTo(Drinks.T.getMoney()) == 1))
            result = true;
        if (selectedDrink.equalsIgnoreCase(Drinks.H.getInitial()) && (givenAmountFromCustomer.compareTo(Drinks.H.getMoney()) == 0
                || givenAmountFromCustomer.compareTo(Drinks.H.getMoney()) == 1))
            result = true;
        if (selectedDrink.equalsIgnoreCase(Drinks.C.getInitial()) && (givenAmountFromCustomer.compareTo(Drinks.C.getMoney()) == 0
                || givenAmountFromCustomer.compareTo(Drinks.C.getMoney()) == 1))
            result = true;
        return result;
    }

    public String sendToDrinkMaker(OrderFromCustomer orderFromCustomer) {
        String result = "";
        if (orderFromCustomer != null) {
            if (isGivenAmountEnough(orderFromCustomer.drinkToString(), orderFromCustomer.currentAmount())) {
                if (isGivenAmountCorrespondingToSelectedDrink(orderFromCustomer.drinkToString(), orderFromCustomer.currentAmount()))
                    result = sendInstructionsToDrinkMaker(orderFromCustomer);
            } else
                result = screenDisplay(screenMissingMoneyDisplay(orderFromCustomer.drinkConstant(), orderFromCustomer.currentAmount()));
        }

        return result;
    }

    @NotNull
    private String screenMissingMoneyDisplay(CoffeMachine.Drinks selectedDrink, BigDecimal orderCurrentAmount) {
        return "Missing " + formatMissingAmount(selectedDrink, orderCurrentAmount) + " cents to complete order";
    }


    private int formatMissingAmount(@NotNull CoffeMachine.Drinks selectedDrink, BigDecimal orderCurrentAmount) {
        return ((selectedDrink.getMoney().subtract(orderCurrentAmount)).multiply(new BigDecimal("100"))).intValueExact();
    }

    public enum Option {
        EH;
    }

    public enum Drinks {
        O(new BigDecimal("0.6"), "O"),
        T(new BigDecimal("0.4"), "T"),
        H(new BigDecimal("0.5"), "H"),
        C(new BigDecimal("0.6"), "C");

        private final BigDecimal money;
        private final String initial;

        Drinks(BigDecimal money, String initial) {
            this.money = money;
            this.initial = initial;
        }

        public BigDecimal getMoney() {
            return money;
        }

        public String getInitial() {
            return initial;
        }

        public enum Option {
            EH;
        }

    }

}
