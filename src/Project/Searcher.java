package Project;

import java.util.*;

public class Searcher {
    private static ArrayList<FirstDish> firstDishes;
    private static ArrayList<SecondDish> secondDishes;
    private static ArrayList<Drink> drinks;
    private static ArrayList<Snack> snacks;
    private static ArrayList<Salad> salads;
    private static HashMap<String, ArrayList<Dish>> subCategories;
    private static final Scanner scan = new Scanner(System.in);
    public static HashSet<String> categories = new HashSet<>(Arrays.asList("Pierwsze Danie", "Drugie Danie",
            "Przekąska", "Napój", "Sałatki"));
    public static HashSet<String> subcategories = new HashSet<>(Arrays.asList("Mięsne", "Wegetariańskie", "Wegańskie",
            "Fit", "Deser", "Jednogarnkowe", "Okazjonalne"));


    public Searcher(ArrayList<FirstDish> firstDishes, ArrayList<SecondDish> secondDishes, ArrayList<Drink> drinks,
                    ArrayList<Snack> snacks, ArrayList<Salad> salads, HashMap<String, ArrayList<Dish>> subCategories) {
        Searcher.firstDishes = firstDishes;
        Searcher.secondDishes = secondDishes;
        Searcher.drinks = drinks;
        Searcher.snacks = snacks;
        Searcher.salads = salads;
        Searcher.subCategories = subCategories;


    }


    public void findByCategory() {
        System.out.println("Jaką kategorię chcesz przeszukać?");
        String cat = scan.nextLine();
        while (!categories.contains(cat)) {
            System.out.println("Kategoria musi należeć do " + categories);
            cat = scan.nextLine();
        }
        boolean validator = false;
        switch (cat) {
            case "Pierwsze Danie":
                if (!firstDishes.isEmpty()) {
                    validator = true;
                    for (FirstDish o : firstDishes) {
                        System.out.println(o.getName());
                    }
                }
                break;
            case "Drugie Danie":
                if (!secondDishes.isEmpty()) {
                    validator = true;
                    for (SecondDish o : secondDishes) {
                        System.out.println(o.getName());
                    }
                }
                break;
            case "Napój":
                if (!drinks.isEmpty()) {
                    validator = true;
                    for (Drink o : drinks) {
                        System.out.println(o.getName());
                    }
                }
                break;
            case "Przekąska":
                if (!snacks.isEmpty()) {
                    validator = true;
                    for (Snack o : snacks) {
                        System.out.println(o.getName());
                    }
                }
                break;
            case "Sałatki":
                if (!salads.isEmpty()) {
                    validator = true;
                    for (Salad o : salads) {
                        System.out.println(o.getName());
                    }
                }
        }
        if (!validator){
            System.out.println("Brak przepisów w danej kategorii");
        }
    }

    public void findBySubcategory() {
        System.out.println("Jaką podkategorię chcesz przeszukać?");
        String sub = scan.nextLine();
        while (!subcategories.contains(sub)) {
            System.out.println("Podkategoria musi należeć do: " + subcategories);
            sub = scan.nextLine();
        }
        if (!subCategories.get(sub).isEmpty())
            for (Dish o : subCategories.get(sub)) {
                System.out.println(o.getName());
            }
        else {
            System.out.println("Brak przepisów w danej podkategorii");
        }
    }

    public boolean val(Dish o, String name) {
        if (o.getName().equals(name)) {
            o.returnContents();
            return true;
        } else return false;
    }

    public void findByName() {
        System.out.println("Jakiej nazwy przepisu szukasz?");
        String name = scan.nextLine();
        boolean validator = false;
        for (Dish o : firstDishes) {
            if (val(o, name)) {
                validator = true;
                break;
            }
        }
        if (!validator) {
            for (Dish o : secondDishes) {
                if (val(o, name)) {
                    validator = true;
                    break;
                }
            }
        }
        if (!validator) {
            for (Dish o : drinks) {
                if (val(o, name)) {
                    validator = true;
                    break;
                }
            }

        }
        if (!validator) {
            for (Dish o : snacks) {
                if (val(o, name)) {
                    validator = true;
                    break;
                }
            }

        }
        if (!validator) {
            for (Dish o : salads) {
                if (val(o, name)) {
                    validator = true;
                    break;
                }
            }

        }
        if (!validator){
            System.out.println("Nie ma takiego przepisu");
        }
    }

    public void chooseFunction(){
        boolean val = true;
        while (val) {
            System.out.println("Według czego chcesz szukać?\n" +
                    "'k' - kategoria\n" +
                    "'p' - podkategoria\n" +
                    "'n' - nazwa\n" +
                    "'m' - powrót do menu");
            String type = scan.nextLine();
            switch (type) {
                case "k":
                    findByCategory();
                    break;
                case "p":
                    findBySubcategory();
                    break;
                case "n":
                    findByName();
                    break;
                case "m":
                    val = false;
                    break;
                default:
                    System.out.println("Zły tryb");
            }
        }
    }
}

