package models;

import play.db.ebean.Model;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class News {
    @Id
    private int id;
    private String title;
    private String description;
    @Basic(fetch=LAZY)
    @Lob
    private byte[] picture;

    @ManyToOne
    private Project project;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
