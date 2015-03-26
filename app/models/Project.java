package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Project extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    private Date startsAt;
    private Date endsAt;
    private String contact;

    @OneToMany
    private List<News> news;

    @OneToOne
    private Address address;

    @OneToMany
    private List<DonationGoal> donationGoals;

    @OneToMany
    private List<Donation> donations;

    @ManyToOne
    private Consumer consumer;

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

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}