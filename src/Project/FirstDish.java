package Project;

import java.util.HashSet;

public class FirstDish extends Dish implements Contents{
    private final int amount;
    public FirstDish(String name, HashSet<String> set, String cat, String sub,
                     int cost, String prep, int amount){
        super(name, set, cat, sub, cost, prep);
        this.amount = amount;
    }

    public int getAmount(){return amount;}

    @Override
    public void returnContents() {
        super.returnContents();
        System.out.println("Ilość: " + getAmount());
    }
}
