package models;

import play.db.ebean.Model;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class News extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Date date;
    private String imageURL;
    @ManyToOne
    private Project project;

    public Project getProject() {
        return project;
    }


    public void setProject(Project project) {
        this.project = project;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public static Finder<Long, News> find = new Finder<>(
            Long.class, News.class
    );
}
