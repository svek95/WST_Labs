package Lab1;

import java.util.Date;

public class Car {
    private String name;
    private Date dateOfStart;
    private String country;
    private double duration;
    private String director;

    public Car(String name, Date date, String country, double duration, String director) {
        this.name = name;
        this.dateOfStart = date;
        this.country = country;
        this.duration = duration;
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
