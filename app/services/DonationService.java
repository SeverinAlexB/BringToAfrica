package services;

import models.Donation;
import models.User;
import models.DonationGoal;

import java.util.List;

public class DonationService {
    public static List<Donation> getDonationsOfDonationGoal(DonationGoal donationGoal){
        return Donation.find.where().eq("donationGoal", donationGoal).findList();
    }
    public static List<Donation> getByUser(User user){
        return Donation.find.where().eq("user",user).findList();
    }
}
