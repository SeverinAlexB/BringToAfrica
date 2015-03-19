package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    private int id;
    private String city;
    private String street;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
