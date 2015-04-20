package services;

import models.DonationType;

public class DonationTypeService {
    public static DonationType getDonationTypeByName(String name){
        return  DonationType.find.where().eq("name", name).findUnique();
    }

    public static DonationType getOrSetDonationType(String name){
        DonationType donationType = getDonationTypeByName(name);
        if (donationType == null) {
            DonationType newDonationType = new DonationType();
            newDonationType.setName(name);
            newDonationType.save();
            return newDonationType;
        } else {
            return donationType;
        }
    }
}
