package viewmodels.donation;


import models.DonationGoal;

import java.util.ArrayList;
import java.util.List;

public class CreateDonationData {

    public CreateDonationData() {
    }

    public CreateDonationData(List<DonationGoal> goals) {
        for (DonationGoal goal : goals) {
            donations.add(goal.getType().getName());
            amounts.add(0);
        }
    }

    public long projectId;
    public List<Integer> amounts = new ArrayList<>();
    public List<String> donations = new ArrayList<>();
    public String remarks;

    public String validate() {
        boolean hasDonation = false;
        for (int amount : amounts) {
            if (amount < 0) {
                return "Anzahl muss grösser gleich 0 sein.";
            }
            if (amount > 0) {
                hasDonation = true;
            }
        }

        if (hasDonation) {
            return null;
        } else {
            return "Es muss mindestens ein Gegenstand gespendet werden";
        }
    }
}
