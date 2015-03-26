package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class DonationType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @OneToMany
    private List<Donation> donations;

    @OneToMany
    private List<DonationGoal> donationGoals;

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
}
