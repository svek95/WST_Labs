package Lab3.Exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "Lab3.Exception.CarServiceFault")
public class IDExcept extends Exception {
    private CarServiceFault _CarServiceFault;

    private final static String TEMPLATE_OF_MESSAGE = "Error during %s operation. " +
            "Film with id %d is not found";

    public IDExcept(Integer id, String command, CarServiceFault fault) {
        super(String.format(TEMPLATE_OF_MESSAGE, command, id));
        this._CarServiceFault = fault;
    }

    public IDExcept(Integer id, String command, CarServiceFault fault, Throwable cause) {
        super(String.format(TEMPLATE_OF_MESSAGE, command, id), cause);
        this._CarServiceFault = fault;
    }

    public CarServiceFault getFaultInfo() {
        return _CarServiceFault;
    }


}
