package Project;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotActiveException;
import java.nio.file.NoSuchFileException;
import java.util.*;

public class FileReader {

    public static ArrayList<SecondDish> listOfSecondDishes = new ArrayList<>();
    public static ArrayList<Drink> listOfDrinks = new ArrayList<>();
    public static ArrayList<FirstDish> listOfFirstDishes = new ArrayList<>();
    public static ArrayList<Snack> listOfSnacks = new ArrayList<>();
    public static ArrayList<Salad> listOfSalads = new ArrayList<>();
    public static HashMap<String,ArrayList<Dish>> subCategorySearch = new HashMap<>();
    public static HashSet<String>  names = new HashSet<String>();
    public static Scanner scanner = new Scanner(System.in);

    public FileReader(){
        subCategorySearch.put("Mięsne", new ArrayList<Dish>());
        subCategorySearch.put("Wegetariańskie", new ArrayList<Dish>());
        subCategorySearch.put("Wegańskie", new ArrayList<>());
        subCategorySearch.put("Fit", new ArrayList<>());
        subCategorySearch.put("Deser", new ArrayList<>());
        subCategorySearch.put("Jednogarnkowe", new ArrayList<>());
        subCategorySearch.put("Okazjonalne", new ArrayList<>());
    }

    public static NodeList MainReader(){
        File file;
        DocumentBuilderFactory dbf;
        DocumentBuilder db;
        Document doc;
        NodeList nodes = null;
        boolean val = true;
        String path = "/Users/dawidpylak/Documents/Studia/Programowanie Obiektowe/PO Project/src/Project/cooking.xml";
        while (val){
        try {
            file = new File(path);
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.parse(file);
            doc.getDocumentElement().normalize();
            nodes = doc.getElementsByTagName("Dish");
            val = false;
        }

        catch (NullPointerException | FileNotFoundException f){
            System.out.println("Plik z bazą danych nie istnieje w domyślnej lokalizacji. Chcesz podać nową? (y/n)");
            String ans = scanner.nextLine();
            if (ans.equals("y")){
                System.out.println("Podaj ścieżkę");
                path = scanner.nextLine();
            }
            else System.exit(1);
        }
        catch (ParserConfigurationException| SAXException | IOException  e){
            e.printStackTrace();
        }
        }
        return nodes;
    }

    public static void FirstDishReader(Node node, String name, HashSet<String> ing, String cat, String subcat,
                                       int cost, String prep){
        Element elem = (Element) node;
        int amount =Integer.parseInt(elem.getElementsByTagName("Amount").item(0).getTextContent());
        FirstDish firstDish = new FirstDish(name, ing, cat, subcat, cost, prep, amount);
        listOfFirstDishes.add(firstDish);
        subCategorySearch.get(subcat).add(firstDish);
        names.add(firstDish.getName());
    }

    public static void SecondDishReader(Node node, String name, HashSet<String> ing, String cat, String subcat,
                                        int cost, String prep){
        Element elem = (Element) node;
        int am = Integer.parseInt(elem.getElementsByTagName("Amount").item(0).getTextContent());
        String dest = elem.getElementsByTagName("Destiny").item(0).getTextContent();
        SecondDish recipe = new SecondDish(name, ing, cat, subcat, cost, prep, am, dest);
        listOfSecondDishes.add(recipe);
        subCategorySearch.get(subcat).add(recipe);
        names.add(recipe.getName());
    }

    public static void DrinkReader(Node node, String name, HashSet<String> ing, String cat, String subcat,
                                   int cost, String prep){
        Element elem = (Element) node;
        String type = elem.getElementsByTagName("Type").item(0).getTextContent();
        int amount = Integer.parseInt(elem.getElementsByTagName("Amount").item(0).getTextContent());
        Drink drink = new Drink(name, ing, cat, subcat, cost, prep, amount, type);
        listOfDrinks.add(drink);
        subCategorySearch.get(subcat).add(drink);
        names.add(drink.getName());
    }

    public static void SnackReader(Node node, String name, HashSet<String> ing, String cat, String subcat,
                                   int cost, String prep){
        Element elem = (Element) node;
        String destiny = elem.getElementsByTagName("Destiny").item(0).getTextContent();
        int amount = Integer.parseInt(elem.getElementsByTagName("Portions").item(0).getTextContent());
        Snack snack = new Snack(name, ing, cat, subcat, cost, prep, destiny, amount);
        listOfSnacks.add(snack);
        subCategorySearch.get(subcat).add(snack);
        names.add(snack.getName());
    }


    public static void SaladReader(Node node, String name, HashSet<String> ing, String cat, String subcat,
                                   int cost, String prep){
        Element elem = (Element) node;
        String destiny = elem.getElementsByTagName("Destiny").item(0).getTextContent();
        String type = elem.getElementsByTagName("Type").item(0).getTextContent();
        Salad salad = new Salad(name, ing, cat, subcat, cost, prep, type, destiny);
        listOfSalads.add(salad);
        subCategorySearch.get(subcat).add(salad);
        names.add(salad.getName());
    }

    public void run() {
        NodeList list = MainReader();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            Element elem = (Element) node;
            String cat = elem.getElementsByTagName("Category").item(0).getTextContent();
            String subcat = elem.getElementsByTagName("Subcategory").item(0).getTextContent();
            String name = elem.getElementsByTagName("Name").item(0).getTextContent();
            HashSet<String> ing = new HashSet<>(Arrays.asList(elem.getElementsByTagName("Ingriedients").item(0).getTextContent().split("\n")));
            int cost = Integer.parseInt(elem.getElementsByTagName("cost").item(0).getTextContent());
            String prep  = elem.getElementsByTagName("Preparing").item(0).getTextContent();
            switch (elem.getElementsByTagName("Category").item(0).getTextContent()){
                case "Pierwsze Danie":
                    FirstDishReader(node, name, ing, cat, subcat, cost, prep);
                    break;
                case "Drugie Danie":
                    SecondDishReader(node, name, ing, cat, subcat, cost, prep );
                    break;
                case "Napój":
                    DrinkReader(node, name, ing, cat, subcat, cost, prep);
                    break;
                case "Przekąska":
                    SnackReader(node, name, ing, cat, subcat, cost, prep);
                    break;
                case "Sałatka":
                    SaladReader(node, name, ing, cat, subcat, cost, prep);
                    break;
                default:
                    System.out.println("Nieznana kategoria " + elem.getElementsByTagName("Name").item(0).getTextContent());
            }

            }
        }

        public ArrayList<SecondDish> getSecondDishes(){return listOfSecondDishes;}
        public ArrayList<Drink> getListOfDrinks(){return listOfDrinks;}
        public ArrayList<FirstDish> getListOfFirstDishes() { return listOfFirstDishes; }
        public ArrayList<Snack> getListOfSnacks() { return listOfSnacks; }
        public ArrayList<Salad> getListOfSalads() { return listOfSalads; }
        public HashMap<String, ArrayList<Dish>> getSubCategorySearch() {return subCategorySearch;}
        public HashSet<String> getNames(){return names;}
}


