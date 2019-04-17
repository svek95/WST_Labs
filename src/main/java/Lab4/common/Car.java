package Lab4.common;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@XmlRootElement
public class Car {

    private String name;
    private Date dateOfSales;
    private String country;
    private double power;
    private String model;

    public Car(String name, Date date, String country, double power, String model) {
        this.name = name;
        this.dateOfSales = date;
        this.country = country;
        this.power = power;
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getdateOfSales() {
        return dateOfSales;
    }

    public void setdateOfSales(Date dateOfSales) {
        this.dateOfSales = dateOfSales;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getpower() {
        return power;
    }

    public void setpower(double power) {
        this.power = power;
    }

    public String getmodel() {
        return model;
    }

    public void setmodel(String model) {
        this.model = model;
    }

}
