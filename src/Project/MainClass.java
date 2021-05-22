package Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MainClass {
    public static void main(String[] args) {
        FileReader reader = new FileReader();
        reader.run();
        ArrayList<FirstDish> firstList = reader.getListOfFirstDishes();
        ArrayList<SecondDish> secondList = reader.getSecondDishes();
        ArrayList<Drink> drinkList = reader.getListOfDrinks();
        ArrayList<Snack> snackList = reader.getListOfSnacks();
        ArrayList<Salad> saladList = reader.getListOfSalads();
        HashMap<String, ArrayList<Dish>> subCategories = reader.getSubCategorySearch();
        HashSet<String> names = reader.getNames();
        NewRecipe getter = new NewRecipe(names);
        Searcher searcher = new Searcher(firstList, secondList, drinkList, snackList, saladList, subCategories);
        Runnable runner = new listThread(firstList, secondList, drinkList, snackList, saladList);
        Thread thread = new Thread(runner);
        userHandler handler = new userHandler(firstList, secondList, drinkList, snackList, saladList,
                subCategories, getter, searcher, thread);
        handler.mainMenu();

    }
}
