package Project;

import java.util.HashSet;

public class Drink extends Dish implements Contents{

    private final String type;
    private final int amount;

    public Drink(String name, HashSet<String> set, String cat, String sub, int cost, String prep, int amount, String type){
        super(name, set, cat, sub, cost, prep);
        this.type = type;
        this.amount = amount;
    }

    public String getType(){return type;}
    public int getAmount(){return amount;}

    @Override
    public void returnContents() {
        super.returnContents();
        System.out.println("Ilosć w litrach:" + getAmount());
        System.out.println("Napój to: " + getType());
    }
}
