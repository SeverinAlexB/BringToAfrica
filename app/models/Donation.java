package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Donation {
    @Id
    private int id;
    private String description;
    //TODO Enum

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
