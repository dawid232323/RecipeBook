package Project;

import java.util.ArrayList;

public class listThread implements Runnable{

    private static ArrayList<FirstDish> firstDishes = new ArrayList<>();
    private static ArrayList<SecondDish> secondDishes = new ArrayList<>();
    private static ArrayList<Drink> drinks = new ArrayList<>();
    private static ArrayList<Snack> snacks = new ArrayList<>();
    private static ArrayList<Salad> salads = new ArrayList<>();

    private static int fDishesSize;
    private static int sDishesSize;
    private static int drinksSize;
    private static int snacksSize;
    private static int saladsSize;

    private static fileWriter writer;

    public listThread(ArrayList<FirstDish> firstDishes, ArrayList<SecondDish> secondDishes,
                      ArrayList<Drink> drinks, ArrayList<Snack> snacks, ArrayList<Salad> salads) {
        listThread.firstDishes = firstDishes;
        listThread.secondDishes = secondDishes;
        listThread.drinks = drinks;
        listThread.snacks = snacks;
        listThread.salads = salads;
        listThread.fDishesSize = firstDishes.size();
        listThread.sDishesSize = secondDishes.size();
        listThread.drinksSize = drinks.size();
        listThread.snacksSize = snacks.size();
        listThread.saladsSize = salads.size();
        listThread.writer = new fileWriter(firstDishes, secondDishes, drinks, snacks, salads);
    }

    public static String checkSize(){
        if (fDishesSize != firstDishes.size()){ return "f"; }
        if (sDishesSize != secondDishes.size()) {return "s"; }
        if (drinksSize != drinks.size()){return "d";}
        if (snacksSize != snacks.size()) {return "sn";}
        if (saladsSize != salads.size()) {return  "sa";}
        else return "";
    }

    @Override
    public void run() {
//        System.out.println("Drugi wątek zaczyna pracę");
        while (true){
            switch (checkSize()){
                case "f":
                    writer.run("Pierwsze Danie");
                    System.out.println("\nZaktualizowałem pierwsze dania w pliku z bazą\n");
                    listThread.fDishesSize = firstDishes.size();
                    break;
                case "s":
                    writer.run("Drugie Danie");
                    System.out.println("\nZaktualizowałem drugie dania w pliku z bazą\n");
                    listThread.sDishesSize = secondDishes.size();
                    break;
                case "d":
                    writer.run("Napój");
                    System.out.println("\nZaktualizowałem napoje w pliku z bazą\n");
                    listThread.drinksSize = drinks.size();
                    break;
                case "sn":
                    writer.run("Przekąska");
                    System.out.println("\nZaktualizowałem przekąski w pliku z bazą\n");
                    listThread.snacksSize = snacks.size();
                    break;
                case "sa":
                    writer.run("Sałatka");
                    System.out.println("\nZaktualizowałem sałatki w pliku z bazą\n");
                    listThread.saladsSize = salads.size();
                    break;
                default:
                    System.out.println("\nWszystkie listy bez zmian\n");

            }
            try {
                Thread.sleep(10000);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
