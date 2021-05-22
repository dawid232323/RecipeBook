package Project;

import java.util.HashSet;

public class Salad extends Dish implements Contents {
    private final String type;
    private final String destiny;

    public Salad(String name, HashSet<String> set, String cat, String sub, int cost, String prep, String type, String destiny) {
        super(name, set, cat, sub, cost, prep);
        this.type = type;
        this.destiny = destiny;
    }

    public String getType(){return type;}

    public String getDestiny() {return destiny; }

    @Override
    public void returnContents() {
        super.returnContents();
        System.out.println("Sałatka ta jest: " + getType());
        System.out.println("Najlepiej jest ją spożywać jako: " + getDestiny());
    }
}
