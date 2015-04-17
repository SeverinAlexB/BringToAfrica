package viewmodels.MyDonations;

import models.Project;

import java.util.ArrayList;

/**
 * Created by Severin on 16.04.2015.
 */
public class ProjectDonationData {
    public Project project;
    public String date;
    public ArrayList<DonationData> donations = new ArrayList<>();
    public String contact;
    public String notice;

}
