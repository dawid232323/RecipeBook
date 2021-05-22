import java.util.ArrayList;
import java.util.Arrays;

public class second {
    private ArrayList<String> testList = new ArrayList<>();

    public second(){
        this.testList.addAll(Arrays.asList("test1", "test2", "test3"));
    }

    public ArrayList<String> getTestList() {
        return testList;
    }

    public void print(){
        for (String s : testList) {
            System.out.println(s);
        }
    }
}
