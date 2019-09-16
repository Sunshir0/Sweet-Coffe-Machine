package fr.sweetcoffe;

public class CoffeMachine {

    public String selectDrink(String drink) {
        String result = "";

        if (!drink.equalsIgnoreCase(Drinks.T.toString())
                && !drink.equalsIgnoreCase(Drinks.H.toString())
                && !drink.equalsIgnoreCase(Drinks.C.toString()))
            result = "Error : Choose between TEA:T, CAFFE:C, CHOCOLATE:H";
        else
            result = drink;

        return result;
    }

    public String addSugar(String numberOfSugars) {
        if (numberOfSugars.equals("1") || numberOfSugars.equals("2"))
            return numberOfSugars;
        return "";
    }

    public String sendInstructionsToDrinkMaker(OrderFromCustomer orderFromCustomer) {
        String instructions = "";
        if (orderFromCustomer.drink().equals(Drinks.T.toString())
                || orderFromCustomer.drink().equals(Drinks.H.toString())
                || orderFromCustomer.drink().equals(Drinks.C.toString()))
            instructions += selectDrink(orderFromCustomer.drink());
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

    public enum Drinks {
        TEA, CHOCOLAT, COFFE,
        T, H, C;
    }

}
