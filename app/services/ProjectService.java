package services;

import models.Donation;
import models.DonationGoal;
import models.Project;

import java.util.List;

public class ProjectService {

    public static void saveProject(Project project){
        project.save();
    }

    public static Project getProjectById(Long id){
        return Project.find.byId(id);
    }

    public static Project getProjectByName(String name){
       return  Project.find.where().eq("name",name).findUnique();
    }

    public static List<Project> getAllProjects(){
        return Project.find.all();
    }

    public static int getStateOfProjectInPercent(Long id){
        Project p = getProjectById(id);
        int goal = 0;
        int state = 0;
        for(DonationGoal dg: p.getDonationGoals()){
            goal += dg.getAmount();
        }
        for(Donation d: p.getDonations()){
            d.get
        }
        p.getDonations();
        return 0;
    };

}