package Project;

import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.ArrayList;

public class fileWriter {

    public static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    public static DocumentBuilder documentBuilder;
    public static Document document;
    public static Element root;

    public static ArrayList<FirstDish> firstDishes = new ArrayList<>();
    public static ArrayList<SecondDish> secondDishes = new ArrayList<>();
    public static ArrayList<Drink> drinks = new ArrayList<>();
    public static ArrayList<Snack> snacks = new ArrayList<>();
    public static ArrayList<Salad> salads = new ArrayList<>();

    public fileWriter(ArrayList<FirstDish> firstDishes, ArrayList<SecondDish> secondDishes,
                      ArrayList<Drink> drinks, ArrayList<Snack> snacks,
                      ArrayList<Salad> salads){
        fileWriter.firstDishes = firstDishes;
        fileWriter.secondDishes = secondDishes;
        fileWriter.drinks = drinks;
        fileWriter.snacks = snacks;
        fileWriter.salads = salads;

        try {
            fileWriter.documentBuilder = documentBuilderFactory.newDocumentBuilder();
            fileWriter.document = documentBuilder.parse("/Users/dawidpylak/Documents/Studia/Programowanie Obiektowe/PO Project/src/Project/cooking.xml");
            fileWriter.root = document.getDocumentElement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void prepareDish(Element main, Dish dish){
        Element category = document.createElement("Category");
        Element subCategory = document.createElement("Subcategory");
        Element name = document.createElement("Name");
        category.appendChild(document.createTextNode(dish.getCategory()));
        subCategory.appendChild(document.createTextNode(dish.getSubcategory()));
        name.appendChild(document.createTextNode(dish.getName()));
        main.appendChild(category);
        main.appendChild(subCategory);
        main.appendChild(name);
        Element ing = document.createElement("Ingredients");
        for (String s: dish.getIngriedients()){
            Element value = document.createElement("value");
            value.appendChild(document.createTextNode(s));
            ing.appendChild(value);
        }
        Attr type = document.createAttribute("type");
        type.setValue("array");
        ing.setAttributeNode(type);

        Element cost = document.createElement("cost");
        Element prep = document.createElement("Preparing");
        cost.appendChild(document.createTextNode(Integer.toString(dish.getCost())));
        prep.appendChild(document.createTextNode(dish.getPreparing()));
        main.appendChild(ing);
        main.appendChild(prep);
    }


    public static void firstDishWriter(){
        int lastElement = firstDishes.size() - 1;
        FirstDish newFirstDish = firstDishes.get(lastElement);
        Element newFirstNode = document.createElement("Dish");
        prepareDish(newFirstNode, newFirstDish);
        Element am = document.createElement("Amount");
        am.appendChild(document.createTextNode(Integer.toString(newFirstDish.getAmount())));
        newFirstNode.appendChild(am);
        root.appendChild(newFirstNode);
    }


    public static void secondDishesWriter() {
        int lastElement = secondDishes.size() - 1;
        SecondDish dish = secondDishes.get(lastElement);
        Element newSecondDish = document.createElement("Dish");
        prepareDish(newSecondDish, dish);
        Element am = document.createElement("Amount");
        Element dest = document.createElement("Destiny");
        am.appendChild(document.createTextNode(Integer.toString(dish.getAmountOfPortions())));
        newSecondDish.appendChild(am);
        dest.appendChild(document.createTextNode(dish.getDestiny()));
        newSecondDish.appendChild(dest);
        root.appendChild(newSecondDish);
    }

    public static void drinkWriter(){
        int lastElem = drinks.size() - 1;
        Drink drink = drinks.get(lastElem);
        Element newDrink = document.createElement("Dish");
        prepareDish(newDrink, drink);
        Element type = document.createElement("Type");
        Element am = document.createElement("Amount");
        type.appendChild(document.createTextNode(drink.getType()));
        am.appendChild(document.createTextNode(Integer.toString(drink.getAmount())));
        newDrink.appendChild(type);
        newDrink.appendChild(am);
        root.appendChild(newDrink);
    }

    public static void snackWriter(){
        Snack snack = snacks.get(snacks.size() - 1);
        Element newSnack = document.createElement("Dish");
        prepareDish(newSnack, snack);
        Element destiny = document.createElement("Destiny");
        Element por = document.createElement("Portions");
        destiny.appendChild(document.createTextNode(snack.getDestiny()));
        por.appendChild(document.createTextNode("Portions"));
        newSnack.appendChild(destiny);
        newSnack.appendChild(por);
        root.appendChild(newSnack);
    }

    public static void saladWriter(){
        Salad salad = salads.get(salads.size() - 1);
        Element newSalad = document.createElement("Dish");
        prepareDish(newSalad, salad);
        Element type = document.createElement("Type");
        Element des = document.createElement("Destiny");
        type.appendChild(document.createTextNode(salad.getType()));
        des.appendChild(document.createTextNode(salad.getDestiny()));
        newSalad.appendChild(type);
        newSalad.appendChild(des);
        root.appendChild(newSalad);
    }

    public void run(String argument){
        switch (argument){
            case "Pierwsze Danie":
                firstDishWriter();
                break;
            case "Drugie Danie":
                secondDishesWriter();
                break;
            case "Napój":
                drinkWriter();
                break;
            case "Przekąska":
                snackWriter();
                break;
            case "Sałatka":
                saladWriter();
                break;
        }

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult("/Users/dawidpylak/Documents/Studia/Programowanie Obiektowe/PO Project/src/Project/cooking.xml");
            transformer.transform(source, result);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
