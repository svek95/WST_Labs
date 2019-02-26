package Lab1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(name = "CarService")
public class CarWebService {

    private final static CarDAO carDao = new StandaloneCarDAO();

    @WebMethod(operationName = "getCars")
    public List<Car> getCars(@WebParam(name = "CarBrand") String name,
                                      @WebParam(name = "model") String model,
                                      @WebParam(name = "country") String country,
                                      @WebParam(name = "dateOfSales") String date,
                                      @WebParam(name = "power") int power) {
        return carDao.getDataByFields(name, model, country, date, power);
    }

    @WebMethod(operationName = "searchCars")
    public List<Car> searchCars(String term) {
        CarDAO dao = new StandaloneCarDAO();
        String name = "", model = "", country = "", date = "";
        int power = -1;
        for (String arg : term.split(";")) {
            if (arg.split("=")[0].equals("CarBrand")) {
                name = arg.split("=")[1];
            }
            if (arg.split("=")[0].equals("model")) {
                model = arg.split("=")[1];
            }
            if (arg.split("=")[0].equals("country")) {
                country = arg.split("=")[1];
            }
            if (arg.split("=")[0].equals("dateOfSales")) {
                date = arg.split("=")[1];
            }
            if (arg.split("=")[0].equals("power")) {
                power = Integer.parseInt(arg.split("=")[1]);
            }
        }
                List<Car> cars = dao.getDataByFields(name, model, country,  date, power);

        return cars;
    }
}
