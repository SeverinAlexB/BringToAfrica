package models;

import play.db.ebean.Model;
import javax.persistence.*;

@Entity
public class Address extends Model {
    @Id
    @GeneratedValue
    private Long id;
    private String country;
    private String city;
    private String street;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public static Finder<Long,Address> find = new Finder<Long,Address>(
            Long.class, Address.class
    );
}
