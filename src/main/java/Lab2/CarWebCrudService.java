package Lab2;


import Lab1.CarWebService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Connection;
import java.util.Date;


@WebService(name = "CarService")
public class CarWebCrudService extends CarWebService{

    private final static CarsDao carsDao = new CarsDao();

    @WebMethod
    public Integer insertCars(
            @XmlElement(required = true)
            @WebParam(name = "Name") String name,
            @WebParam(name = "model") String model,
            @WebParam(name = "country") String country,
            @WebParam(name = "dateOfSales") Date dateOfSales,
            @WebParam(name = "power") int power) {
        return CarsDao.insertDB(name, model, country, dateOfSales, power);
    }

    @WebMethod
    public String updateCars(@XmlElement(required = true)
                             @WebParam(name = "CarsId") Integer id,
                             @WebParam(name = "Name") String name,
                             @WebParam(name = "model") String model,
                             @WebParam(name = "country") String country,
                             @WebParam(name = "dateOfSales") Date dateOfSales,
                             @WebParam(name = "power") int power) {
        return CarsDao.updateDB(id, name, model, country, dateOfSales, power).getStringValue();
    }

    @WebMethod
    public String deleteCars(@XmlElement(required = true)
                             @WebParam(name = "CarsId") Integer id) {
        return CarsDao.deleteDB(id).getStringValue();
    }

}
