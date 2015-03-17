package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project extends Model {

    @Id
    public String id;

    public String title;
    public String description;
    //public Date startsAt;
    //public Date endsAt;
    public String contact;
}