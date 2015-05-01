package viewmodels.donation;

import models.Project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;


public class ProjectDonationData implements Comparable<ProjectDonationData> {
    public Project project;
    public String date;
    public ArrayList<DonationData> donations = new ArrayList<>();
    public String contact;
    public String notice;

    @Override
    public int compareTo(ProjectDonationData o) {
        try{
            String format = "dd.MM.yyyy";
            Date d1 = new SimpleDateFormat(format).parse(this.date);
            Date d2 = new SimpleDateFormat(format).parse(o.date);
            return d1.compareTo(d2);
        } catch(Exception ex){
            System.out.println("should no happen - ProjectDonationData compareTo Exception!");
            return 0;
        }

    }
}
