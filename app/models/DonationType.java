package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class DonationType extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Donation> donations;

    public void addDonation(Donation donation){
        donations.add(donation);
    }

    @OneToMany(cascade=CascadeType.ALL)
    private List<DonationGoal> donationGoals;

    public void addDonationGoal(DonationGoal donationGoal){
        donationGoals.add(donationGoal);
    }


    public List<Donation> getDonations() {
        return donations;
    }


    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public List<DonationGoal> getDonationGoals() {
        return donationGoals;
    }

    public void setDonationGoals(List<DonationGoal> donationGoals) {
        this.donationGoals = donationGoals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Finder<Long,DonationType> find = new Finder<Long,DonationType>(
            Long.class, DonationType.class
    );
}
