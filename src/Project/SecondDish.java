package Project;

import java.util.HashSet;

public class SecondDish extends Dish implements Contents{
    private final int amountOfPortions;
    private final String destiny;

    public SecondDish(String name, HashSet<String> set, String cat, String sub, int cost, String prep, int por, String dest){
        super(name, set, cat, sub, cost, prep);
        this.amountOfPortions = por;
        this.destiny = dest;
    }

    public int getAmountOfPortions(){return amountOfPortions;}
    public String getDestiny(){return destiny;}

    @Override
    public void returnContents() {
        super.returnContents();
        System.out.println("Liczba porcji: " + amountOfPortions);
        System.out.println("Potrawa jest przeznaczona na:" + destiny);
    }
}
