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
    private int amount;
    private String messageToCollector;

    @ManyToOne
    User user;

    @ManyToOne
    DonationGoal donationGoal;

    public Donation(User user, DonationGoal donationGoal){
        this.user = user;
        this.donationGoal = donationGoal;
    }

    public DonationGoal getDonationGoal() {
        return donationGoal;
    }

    public void setDonationGoal(DonationGoal donationGoal) {
        this.donationGoal = donationGoal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

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

    public static Finder<Long,Donation> find = new Finder<Long,Donation>(
            Long.class, Donation.class
    );
}
