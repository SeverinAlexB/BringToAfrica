package services;

import models.Donation;

/**
 * Created by SKU on 13.04.2015.
 */
public class DonationService {
    public static Donation getProjectById(Long id){
        return Donation.find.byId(id);
    }

}
