package services;

import models.Donation;
import models.User;

import java.util.List;

/**
 * Created by SKU on 13.04.2015.
 */
public class DonationService {
    public static Donation getProjectById(Long id){
        return Donation.find.byId(id);
    }
    public static List<Donation> getByUser(User user){
        return Donation.find.where().eq("user",user).findList();
    }
}
