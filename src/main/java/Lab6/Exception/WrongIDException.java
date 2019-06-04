package Lab6.Exception;

public class WrongIDException extends Exception {

    private final static String TEMPLATE_OF_MESSAGE = "Error operation Car id is not found";

    public WrongIDException(Integer id, String command ){
        super(String.format(TEMPLATE_OF_MESSAGE, id, command));
    }
}
