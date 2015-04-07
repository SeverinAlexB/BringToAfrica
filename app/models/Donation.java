package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Donation extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;
    private Date date;
    private String messageToCollector;

    //@ManyToOne
    //private Project project;

    @ManyToOne
    private DonationType donationType;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessageToCollector() {
        return messageToCollector;
    }

    public void setMessageToCollector(String messageToCollector) {
        this.messageToCollector = messageToCollector;
    }
}
