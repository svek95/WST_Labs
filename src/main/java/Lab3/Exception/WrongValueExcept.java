package Lab3.Exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "Lab3.Exception.CarServiceFault")
public class WrongValueExcept extends Exception{
    private CarServiceFault _CarServiceFault;

    private static final String MESSAGE_TEMPLATE = "Ooops, you try field %s value must be %s";

    public WrongValueExcept(String fieldName, String possibleValues, CarServiceFault fault) {
        super(String.format(MESSAGE_TEMPLATE, fieldName, possibleValues));
        this._CarServiceFault = fault;
    }

    public WrongValueExcept(String fieldName, String possibleValues, CarServiceFault fault, Throwable cause) {
        super(String.format(MESSAGE_TEMPLATE, fieldName, possibleValues), cause);
        this._CarServiceFault = fault;
    }

    public CarServiceFault getFaultInfo() {
        return _CarServiceFault;
    }



}
