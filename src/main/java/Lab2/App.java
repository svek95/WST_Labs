package Lab2;


import javax.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {
        String url = "http://localhost:8081/CarService";
        Endpoint.publish(url, new CarWebCrudService());
    }
}
