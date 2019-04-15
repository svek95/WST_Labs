package Lab3.Exception;

public class CarServiceFault {
    private String message;
    private final static String TEMPLATE_MESSAGE = "Problem during %s operation. %s";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CarServiceFault(String operation, String message) {
        this.message = String.format(TEMPLATE_MESSAGE, operation, message);
    }
}
