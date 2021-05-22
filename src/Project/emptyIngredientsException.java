package Project;

public class emptyIngredientsException extends Exception{
    public emptyIngredientsException(){

    }

    @Override
    public String getMessage() {
        return "Lista składników nie może być pusta. Podaj przynajmniej jeden składnik";
    }
}
