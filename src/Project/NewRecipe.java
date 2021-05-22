package Project;

import java.util.*;

public class NewRecipe {

    public static Scanner scan = new Scanner(System.in);
    public static String name;
    public static String category;
    public static String subcategory;
    public static HashSet<String> ingredients = new HashSet<>();
    public static int cost;
    public static String preparing;
    public static HashSet<String> categories = new HashSet<>(Arrays.asList("Pierwsze Danie", "Drugie Danie",
            "Przekąska", "Napój", "Sałatki"));
    public static HashSet<String> subcategories = new HashSet<>(Arrays.asList("Mięsne", "Wegetariańskie", "Wegańskie",
            "Fit", "Deser", "Jednogarnkowe", "Okazjonalne"));
    private static HashSet<String> names;


    public NewRecipe(HashSet<String> names) {
        NewRecipe.names = names;
    }

    public static boolean cat_validator(String category){ return categories.contains(category); }

    public static boolean subcat_validator(String subcategory){return subcategories.contains(subcategory);}

    public static FirstDish firstDishGetter(){
        System.out.println("Podaj ile litrów zupy powstanie");
        int amount = Integer.parseInt(scan.nextLine());
        return new FirstDish(name, ingredients, category, subcategory, cost, preparing, amount);
    }

    public static SecondDish secondDishGetter(){
        System.out.println("Podaj liczbę porcji");
        int portions = Integer.parseInt(scan.nextLine());
        System.out.println("Podaj przeznaczenie potrawy");
        String destiny = scan.nextLine();
        return new SecondDish(name, ingredients, category, subcategory, cost, preparing, portions, destiny);
    }

    public static Drink drinkGetter(){
        System.out.println("Podaj jaki to napój");
        String type = scan.nextLine();
        System.out.println("Podaj liczbę litrów");
        int am = Integer.parseInt(scan.nextLine());
        return new Drink(name, ingredients, category, subcategory, cost, preparing, am, type);
    }

    public static Snack snackGetter(){
        System.out.println("Podaj przeznaczenie przystawki");
        String dest = scan.nextLine();
        System.out.println("Podaj ilość poracji");
        int am = Integer.parseInt(scan.nextLine());
        return new Snack(name, ingredients, category, subcategory, cost, preparing, dest, am);
    }

    public static Salad saladGetter(){
        System.out.println("Podaj rodzaj sałatki");
        String type = scan.nextLine();
        String dest = scan.nextLine();
        return new Salad(name, ingredients, category, subcategory, cost, preparing, type, dest);
    }

    public void adder(ArrayList<FirstDish> fDish, ArrayList<SecondDish> sDish, ArrayList<Drink> drinks,
                      ArrayList<Salad> salads, ArrayList<Snack> snacks,
                      HashMap<String, ArrayList<Dish>> dict){
        System.out.println("Podaj kategorię przepisu");
        category = scan.nextLine();
        while (!cat_validator(category)){
            System.out.println("Kategoria musi należeć do: " + categories);
            category = scan.nextLine();
        }
        System.out.println("Podaj nazwę przepisu");
        name = scan.nextLine();
        while (names.contains(name)){
            System.out.println("Podana nazwa już istnieje, podaj jakąś inną");
            name = scan.nextLine();
        }
        System.out.println("Podaj podkategorię przepisu");
        subcategory = scan.nextLine();
        while (!subcat_validator(subcategory)){
            System.out.println("Podkategoria musi należeć do: " + subcategories);
            subcategory = scan.nextLine();
        }

        System.out.println("Podawaj składniki, aby zakończyć wpisz end");
        String ing = scan.nextLine();
        try {
            if (ing.equals("end")){
                throw new emptyIngredientsException();
            }
        }
        catch (emptyIngredientsException exception){
            exception.printStackTrace();
            System.out.println(exception.getMessage());
            ing = null;
        }
        while (ing == null || !ing.equals("end")){
            ingredients.add(ing);
            ing = scan.nextLine();
        }
        System.out.println("Podaj Postępowanie");
        preparing = scan.nextLine();
        System.out.println("Podaj koszt");
        cost = Integer.parseInt(scan.nextLine());

        switch (category){
            case "Pierwsze Danie":
                fDish.add(firstDishGetter());
                dict.get(subcategory).add(fDish.get(fDish.size() - 1));
                break;
            case "Drugie Danie":
                sDish.add(secondDishGetter());
                dict.get(subcategory).add(sDish.get(sDish.size() - 1));
                break;
            case "Przekąska":
                snacks.add(snackGetter());
                dict.get(subcategory).add(snacks.get(snacks.size() - 1));
                break;
            case "Napój":
                drinks.add(drinkGetter());
                dict.get(subcategory).add(drinks.get(drinks.size() - 1));
                break;
            case "Sałatki":
                salads.add(saladGetter());
                dict.get(subcategory).add(salads.get(salads.size() - 1));
                break;
        }
    }
}
