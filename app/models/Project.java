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
    private String imageURL;
    private Date startsAt;
    private Date endsAt;
    private String contact;

    //@ManyToOne
    //private Consumer consumer;

    @OneToMany(cascade=CascadeType.ALL)
    private List<News> news;

    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

    @OneToMany(cascade=CascadeType.ALL)
    private List<DonationGoal> donationGoals;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Donation> donations;

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

    public String getImageURL() { return imageURL; }

    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

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

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<DonationGoal> getDonationGoals() {
        return donationGoals;
    }

    public void setDonationGoals(List<DonationGoal> donationGoals) {
        this.donationGoals = donationGoals;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public static Finder<Long,Project> find = new Finder<Long,Project>(
            Long.class, Project.class
    );

}