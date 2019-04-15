package Lab3;

import Lab1.CarWebService;
import Lab3.Exception.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

@WebService(name = "CarService")
public class CarWebExcept extends CarWebService {

    private final static CarDao carDao = new CarDao();

    @WebMethod
    public Integer insertCar(
//            @WebParam(name = "CarID") Integer id,
            @WebParam(name = "Name") String name,
            @WebParam(name = "model") String model,
            @WebParam(name = "country") String country,
            @WebParam(name = "dateOfSales") Date dateOfSales,
            @WebParam(name = "power") int power) throws  WrongValueExcept {
        if (power < 1 || power > 2000) {
            CarServiceFault fault = new CarServiceFault("insert", "ERROR insert, power not value");
            throw new WrongValueExcept("insert", "Power must be between 1 PS and 2000 PS", fault);
        } else {
            return carDao.insertDB(name, model, country, dateOfSales, power);
        }
    }

    @WebMethod
    public String updateCar(@XmlElement(required = true)
                            @WebParam(name = "CarID") Integer id,
                            @WebParam(name = "Name") String name,
                            @WebParam(name = "model") String model,
                            @WebParam(name = "country") String country,
                            @WebParam(name = "dateOfSales") Date dateOfSales,
                            @WebParam(name = "power") int power) throws IDExcept, WrongValueExcept {
        if (carDao.FindID(id)) {
            if (power < 1 || power > 2000) {
                CarServiceFault fault = new CarServiceFault("insert", "ERROR insert, power not value");
                throw new WrongValueExcept("insert", "Power must be between 1 PS and 2000 PS", fault);
            } else {
                return carDao.updateDB(id, name, model, country, dateOfSales, power).getStringValue();
            }
        } else {
            CarServiceFault fault = new CarServiceFault("update", "Row update");
            throw new IDExcept(id, "update", fault);
        }

    }


    @WebMethod
    public String deleteCar(@XmlElement(required = true)
                            @WebParam(name = "CarID") Integer id) throws IDExcept {
        if (carDao.FindID(id)) {
            return carDao.deleteDB(id).getStringValue();
        } else {
            CarServiceFault fault = new CarServiceFault("delete", "Row delete must");
            throw new IDExcept(id, "delete", fault);
        }
    }


}
