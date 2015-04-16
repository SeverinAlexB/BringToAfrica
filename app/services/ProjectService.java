package services;

import com.avaje.ebean.PagingList;
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

    public static PagingList<Project> getPageingListOfProjects(int pageSize){
        return Project.find.where().findPagingList(pageSize);
    }

    public static int getStateOfProjectInPercent(Project project){
        int goal = 0;
        int state = 0;
        for(DonationGoal dg: project.getDonationGoals()){
            System.out.println(dg.getAmount());
            goal += dg.getAmount();
        }
        if(goal == 0)return 0;
        for(Donation d: project.getDonations()){
            System.out.println(d.getAmount());
            state += d.getAmount();
        }
        if(state == 0)return 0;
        return 100 / goal * state;
    };

}