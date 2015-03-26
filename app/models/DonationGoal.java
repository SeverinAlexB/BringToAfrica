package models;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class DonationGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int amount;

    @ManyToOne
    private Project project;

    @ManyToOne
    private DonationType donationType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
