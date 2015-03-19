package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DonationGoal {
    @Id
    private int id;
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
