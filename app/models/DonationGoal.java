package models;

import play.db.ebean.Model;

import javax.persistence.*;

@Entity
public class DonationGoal extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int amount;

    //@ManyToOne
    //private Project project;

    @OneToMany(cascade=CascadeType.ALL)
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


    public DonationType getDonationType() {
        return donationType;
    }

    public void setDonationType(DonationType donationType) {
        this.donationType = donationType;
    }

    public static Finder<Long,DonationGoal> find = new Finder<Long,DonationGoal>(
            Long.class, DonationGoal.class
    );
}
