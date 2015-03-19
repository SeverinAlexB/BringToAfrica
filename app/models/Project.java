package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Project extends Model {

    @Id
    private String id;
    private String title;
    private String description;
    private Date startsAt;
    private Date endsAt;
    private String contact;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}