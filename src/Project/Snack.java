package Project;

import Project.Dish;

import java.util.HashSet;

public class Snack extends Dish implements Contents{
    private final String destiny;
    private final int amount;

    public Snack(String name, HashSet<String> set, String cat, String sub, int cost, String prep,
                 String dets, int amount){
        super(name, set, cat, sub, cost, prep);
        this.amount = amount;
        this.destiny = dets;
    }

    public int getAmount(){return amount;}
    public String getDestiny(){return destiny;}

    @Override
    public void returnContents() {
        super.returnContents();
        System.out.println("Przekąska jest przznaczona do: " + getDestiny());
        System.out.println("Ilość: " + getAmount());
    }
}
