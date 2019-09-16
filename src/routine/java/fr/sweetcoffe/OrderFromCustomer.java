package fr.sweetcoffe;

public class OrderFromCustomer {
    private CoffeMachine.Drinks drink;
    private String sugars;

    public OrderFromCustomer(CoffeMachine.Drinks drink) {
        this.drink = drink;
        this.sugars = "";

    }

    public OrderFromCustomer(int sugars, int stick) {
        this.sugars = String.valueOf(sugars);
    }

    public OrderFromCustomer(CoffeMachine.Drinks drink, int sugars) {
        this.drink = drink;
        this.sugars = String.valueOf(sugars);
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


}
