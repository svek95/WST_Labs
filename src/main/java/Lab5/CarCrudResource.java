package Lab5;

//import Lab2.CheckStatus;
import Lab4.common.Car;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("/cars")
@Produces({MediaType.APPLICATION_JSON})
public class CarCrudResource {

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

    /// seve
    @POST
    @Path("/saves")
    public String insertCars(@QueryParam("Name") String name,
                             @QueryParam("model") String model,
                             @QueryParam("country") String country,
                             @QueryParam("dateOfSales") Date dateOfSales,
                             @QueryParam("power") Integer power) {
        return carDao.insertDB(name, model, country, dateOfSales, power).toString();
    }

    /// Update id
    @PUT
    @Path("/update/ID")
    public String updateCars(@QueryParam("id") Integer id,
                                  @QueryParam("Name") String name,
                                  @QueryParam("model") String model,
                                  @QueryParam("country") String country,
                                  @QueryParam("dateOfSales") Date dateOfSales,
                                  @QueryParam("power") Integer power) {
        return String.valueOf(carDao.updateDB(id, name, model, country, dateOfSales, power));
    }

    /// Delete

    @Path("/delete/ID")
    @DELETE
    public String deleteCars(@QueryParam("id") Integer id) {

        return carDao.deleteDB(id).getStringValue();
    }



}
