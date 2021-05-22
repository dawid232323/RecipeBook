package Project;

import java.util.HashSet;

public abstract class Dish implements Contents {
        private final String name;
        private final HashSet<String> ingriedients;
        private final String category;
        private final String subcategory;
        private final int cost;
        private final String preparing;

        public Dish(String name, HashSet<String> set, String cat, String sub, int cost, String prep ){
            this.name = name;
            this.category = cat;
            this.ingriedients = set;
            this.subcategory = sub;
            this.cost = cost;
            this.preparing = prep;
        }

        public String getName(){ return name; }

        public String getCategory(){ return category; }

        public String getSubcategory(){ return subcategory; }

        public String getPreparing(){ return preparing; }

        public HashSet<String> getIngriedients(){ return ingriedients; }

        public int getCost(){return cost;}

    @Override
    public void returnContents() {
        System.out.println("Nazwa przepisu: " + name);
        System.out.println("Kategoria przepisu: " + category);
        System.out.println("Podkategoria przepisu: " + subcategory);
        System.out.println("Przygotowanie: " + preparing);
        System.out.println("Składniki: " + ingriedients);
        System.out.println("Szacowany koszt: " + cost);


    }
}
