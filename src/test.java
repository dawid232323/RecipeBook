import javax.print.DocFlavor;
import java.util.*;

public class test {

    public static void add(ArrayList<String> lista){
        lista.add("siema");
        lista.add("elo");
    }

    public static void main(String[] args) {
        second te = new second();
        ArrayList<String> nazwa = te.getTestList();
        te.print();
        add(nazwa);
        te.print();



    }
}
