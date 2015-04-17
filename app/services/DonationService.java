package services;

import models.Donation;
import models.DonationGoal;

import java.util.List;

public class DonationService {
    public static List<Donation> getDonationsOfDonationGoal(DonationGoal donationGoal){
        return Donation.find.where().eq("donationGoal", donationGoal).findList();
    }
}
