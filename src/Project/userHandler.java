package Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class userHandler {
    private static ArrayList<FirstDish> firstDishes;
    private static ArrayList<SecondDish> secondDishes;
    private static ArrayList<Drink> drinks;
    private static ArrayList<Snack> snacks;
    private static ArrayList<Salad> salads;
    private static HashMap<String, ArrayList<Dish>> subCategories;
    private static final Scanner scanner = new Scanner(System.in);
    private static NewRecipe newRecipe;
    private static Searcher searcher;
    public static fileWriter writer;
    public static Thread thread;

    public userHandler(ArrayList<FirstDish> firstDishes, ArrayList<SecondDish> secondDishes, ArrayList<Drink> drinks,
                       ArrayList<Snack> snacks, ArrayList<Salad> salads,
                       HashMap<String, ArrayList<Dish>> subCategories, NewRecipe newRecipe,
                       Searcher searcher, Thread thread){
        userHandler.firstDishes = firstDishes;
        userHandler.secondDishes = secondDishes;
        userHandler.drinks = drinks;
        userHandler.snacks = snacks;
        userHandler.salads = salads;
        userHandler.subCategories = subCategories;
        userHandler.newRecipe = newRecipe;
        userHandler.searcher = searcher;
        userHandler.writer = new fileWriter(firstDishes, secondDishes, drinks, snacks, salads);
        userHandler.thread = thread;
    }

    public static void printHelp(){
        System.out.println("'s' - szukanie przepisów\n" +
                "'n' - dodaj nowy przepis\n" +
                "'k' - zakończ program");
        System.out.println("System na bieżąco sprawdza stan ilości przepisów i na bieżąco je zapisuje do pliku");
    }

    public void mainMenu(){
        System.out.println("Witaj w książce kucharskiej!");
        String oper;
        boolean val = true;
        thread.start();
//        secondDishes.get(secondDishes.size() - 1).returnContents();
        while (val){
            System.out.println("Do jakiej sekcji chcesz przejść? (pomoc - wyświetl dostępne instrukcje)");
            oper = scanner.nextLine();
            switch (oper){
                case "pomoc":
                    printHelp();
                    break;
                case "n":
                    newRecipe.adder(firstDishes, secondDishes, drinks, salads, snacks, subCategories);
//                    secondDishes.get(secondDishes.size()- 1).returnContents();
                    break;
                case "s":
                    searcher.chooseFunction();
                    break;
                case "k":
                    val = false;
                    System.out.println("Do widzenia, miłego dnia!");
                    break;
                default:
                    System.out.println("Zły tryb");
            }
        }
        thread.stop();
    }
}
