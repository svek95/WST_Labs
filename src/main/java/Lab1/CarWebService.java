package Lab1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService(name = "FilmService")
public class CarWebService {

    private final static CarDAO filmDao = new StandaloneCarDAO();

    @WebMethod
    public List<Car> getFilmsByFields(@WebParam(name = "filmName") String name,
                                      @WebParam(name = "director") String director,
                                      @WebParam(name = "country") String country,
                                      @WebParam(name = "dateOfStart") Date date,
                                      @WebParam(name = "duration") Double duration) {
        return filmDao.getDataByFields(name, director, country, date, duration);
    }
}
