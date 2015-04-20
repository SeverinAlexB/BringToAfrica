package services;

import com.avaje.ebean.Page;
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

    public static Page<Project> getProjectPage(int pageSize, int page){
        return Project.find.where().orderBy("endsAt DESC").findPagingList(pageSize).getPage(page);
    }

    public static int getStateOfProjectInPercent(Project project){
        int goal = 0;
        int state = 0;
        for(DonationGoal dg: DonationGoalService.getDonationGoalsOfProject(project)){
            goal += dg.getAmount();
            for(Donation d: DonationService.getDonationsOfDonationGoal(dg)){
                state += d.getAmount();
            }
        }
        if(goal == 0 || state == 0)return 0;
        return 100 / goal * state;
    }
}