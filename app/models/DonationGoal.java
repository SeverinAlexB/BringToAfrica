package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class DonationGoal extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int amount;
    @ManyToOne
    private Project project;
    @OneToMany(mappedBy = "donationGoal")
    private List<Donation> donations;
    @ManyToOne
    private DonationType type;

    public DonationGoal(Project project){
        this.project = project;
    }
    public DonationGoal(){}

    public DonationType getType() {
        return type;
    }

    public void setType(DonationType type) {
        this.type = type;
    }

    public List<Donation> getDonations() {
        return donations;
    }



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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public static Finder<Long,DonationGoal> find = new Finder<>(
            Long.class, DonationGoal.class
    );
}
