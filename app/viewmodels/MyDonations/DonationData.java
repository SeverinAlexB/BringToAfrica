package viewmodels.MyDonations;

import models.Donation;
import models.DonationType;

/**
 * Created by Severin on 16.04.2015.
 */
public class DonationData {
    public DonationData(){}
    public DonationData(Donation donation){
        this.amount = donation.getAmount();
        this.type = donation.getDonationGoal().getType().getName();
    }
    public String type;
    public int amount;
}
