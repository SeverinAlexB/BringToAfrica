package services;

import com.avaje.ebean.Ebean;
import models.Project;
import play.db.ebean.Model;

import java.util.List;

public class ProjectService {

    public void saveProject(Project project){
        project.save();
    }

    public Project getProjectById(Long id){
        Model.Finder<Long, Project> finder = new Model.Finder<>(Long.class, Project.class);
        return finder.byId(id);
    }

    public Project getProjectByName(String name){
        Model.Finder<String, Project> finder = new Model.Finder<>(String.class, Project.class);
        return finder.where().eq("name", name).findUnique();
    }

    public List<Project> getAllProjects(){
        List<Project> projects = new Model.Finder<>(String.class, Project.class).all();
        return projects;
    }

}