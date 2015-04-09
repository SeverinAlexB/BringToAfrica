package services;

import com.avaje.ebean.Ebean;
import models.Project;

import java.util.List;

public class ProjectService {

    public void saveProject(Project project){
        project.save();
    }

    public Project getProjectById(Long id){
        return Ebean.find(Project.class, id);
    }

    public Project getProjectByName(String name){
        return Ebean.find(Project.class).where().eq("name", name).findUnique();
    }

    public List<Project> getAllProjects(){
        return Ebean.find(Project.class).findList();
    }

}