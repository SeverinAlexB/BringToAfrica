package viewmodels.donation;

import models.Donation;


public class DonationData {
    public DonationData() {}
    public DonationData(Donation donation) {
        this.amount = donation.getAmount();
        this.type = donation.getDonationGoal().getType().getName();
    }
    public String type;
    public int amount;
    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}
