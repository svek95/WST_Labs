package Lab6;

//import Lab3.Exception.CarServiceFault;
//import Lab3.Exception.WrongValueExcept;
import Lab6.Exception.WrongValueException;
import Lab6.Exception.WrongIDException;
import Lab4.common.Car;
import Lab5.CarsDAO;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;


@Path("/cars")
@Produces({MediaType.APPLICATION_JSON})
public class CarExceptionsResource {
    private final static CarsDAO carDao = new CarsDAO();

    /// Find
    @GET
    public List<Car> findCars(@QueryParam("Name") String name,
                              @QueryParam("model") String model,
                              @QueryParam("country") String country,
                              @QueryParam("dateOfSales") Date dateOfSales,
                              @QueryParam("power") Integer power) {
        return carDao.getDataByFields(name, model, country, dateOfSales, power);
    }

    /// insert
    @POST
    @Path("/saves")
    public Integer insertCars(@QueryParam("Name") String name,
                              @QueryParam("model") String model,
                              @QueryParam("country") String country,
                              @QueryParam("dateOfSales") Date dateOfSales,
                              @QueryParam("power") Integer power) throws WrongValueException {
        if (power < 1 || power > 2000) {
//            CarServiceFault fault = new CarServiceFault("insert", "ERROR insert, power not value");
            throw new WrongValueException("insert", "Power must be between 1 PS and 2000 PS");
        } else {
            return carDao.insertDB(name, model, country, dateOfSales, power);
        }
    }


    /// Update id
    @PUT
    @Path("/update/ID")
    public String updateCars(@QueryParam("id") Integer id,
                             @QueryParam("Name") String name,
                             @QueryParam("model") String model,
                             @QueryParam("country") String country,
                             @QueryParam("dateOfSales") Date dateOfSales,
                             @QueryParam("power") Integer power) throws WrongIDException, WrongValueException{
        if (carDao.findID(id)){
            if (power < 1 || power > 2000) {
                throw new WrongValueException("insert","Power must be between 1 PS and 2000 PS");
            } else {
                return carDao.updateDB(id, name, model, country, dateOfSales, power).getStringValue();
            }

        } else {
            throw new WrongIDException(id, "update");
        }


        //return String.valueOf(carDao.updateDB(id, name, model, country, dateOfSales, power));
    }



    /// Delete

    @Path("/delete/ID")
    @DELETE
    public String deleteCars(@QueryParam("id") Integer id) throws WrongIDException {

        if (carDao.findID(id)){
            return carDao.deleteDB(id).getStringValue();
        } else {
            throw new WrongIDException(id, "delete");
        }

    }
}
