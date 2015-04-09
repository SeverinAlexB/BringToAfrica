package services;

import models.Project;

import java.util.List;

public class ProjectService {

    public void saveProject(Project project){
        project.save();
    }

    public Project getProjectById(Long id){
        return Project.find.byId(id);
    }

    public Project getProjectByName(String name){
       return  Project.find.where().eq("name",name).findUnique();
    }

    public List<Project> getAllProjects(){
        return Project.find.all();
    }

}