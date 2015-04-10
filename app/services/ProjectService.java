package services;

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

}