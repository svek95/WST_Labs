package Lab6.Exception;

public class WrongValueException extends Exception {
    private static final String  MESSAGE_TEMPLATE = "Field value";

    public WrongValueException(String FeilName, String possValues){
        super(String.format(MESSAGE_TEMPLATE, FeilName, possValues));
    }
}
