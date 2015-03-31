package models;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class Donation extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;
    //TODO Enum

    @ManyToOne
    private Project project;

    @ManyToOne
    private DonationType donationType;

    @ManyToOne
    private Consumer consumer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
