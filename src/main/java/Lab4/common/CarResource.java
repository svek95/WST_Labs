package Lab4.common;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.Date;
import java.util.List;

@Path("/cars")
@Produces({MediaType.APPLICATION_JSON})
public class CarResource {

    private final static CarDAO carDao = new StandaloneCarDAO();

    @GET
    public List<Car> findCars(@QueryParam("Name") String name,
                                @QueryParam("model") String model,
                                @QueryParam("country") String country,
                                @QueryParam("dateOfSales") Date dateOfSales,
                                @QueryParam("power") Integer power) {
        return carDao.getDataByFields(name, model, country, dateOfSales, power);
    }


}
