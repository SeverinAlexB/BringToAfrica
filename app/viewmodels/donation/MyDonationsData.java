package viewmodels.donation;

import models.Project;

import java.util.ArrayList;
import java.util.Collections;


public class MyDonationsData {
    public ArrayList<ProjectDonationData> donations = new ArrayList<>();
    public ProjectDonationData getOrSetData(Project project, String date, String notice){
        for (ProjectDonationData data: donations) {
            if (project.getId() == data.project.getId() && date.equals(data.date)) {
                return data;
            }
        }
        ProjectDonationData  pdata = new ProjectDonationData();
        pdata.project = project;
        pdata.date = date;
        pdata.contact = project.getContact();
        pdata.notice = notice;
        donations.add(pdata);
        Collections.sort(donations);
        return pdata;
    }

}
