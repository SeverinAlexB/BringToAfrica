package controllers;


import models.DonationGoal;
import models.DonationType;
import models.Project;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import controllers.forms.Converter;
import controllers.forms.ProjectData;
import play.mvc.Security;
import views.html.newProject;

import java.util.ArrayList;
import java.util.List;
import services.ProjectService;



public class Projects extends Controller {
    private static ProjectService projectService = new ProjectService();

    public static Result getProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ok(views.html.index.render(projects));
    }

    public static Result getProject(long id) {
        Project project = projectService.getProjectById(id);
        return ok(views.html.detail.render(project));
    }

    //@Security.Authenticated(Secured.class)
    public static Result addProjectData() throws  AfricaException{
        Form<ProjectData> projectDataForm = new Form<>(ProjectData.class);
        projectDataForm = Form.form(ProjectData.class).bindFromRequest();
        if (projectDataForm.hasErrors()) {
            System.out.println("Projectdata has errors");
            return badRequest(newProject.render(projectDataForm));
        } else {
            models.Project project = new models.Project();
            List<DonationGoal> donationGoalList = new ArrayList<>();
            models.Address address = new models.Address();

            project.setTitle(projectDataForm.get().title);
            project.setDescription(projectDataForm.get().description);
            project.setEndsAt(Converter.stringToSqlDate(projectDataForm.get().endsAt));
            project.setStartsAt(Converter.stringToSqlDate(projectDataForm.get().startsAt));
            project.setContact(projectDataForm.get().contact);

            int i = 0;
            for(String amount: projectDataForm.get().amounts){
                DonationType donationType = new DonationType();
                DonationGoal donationGoal = new DonationGoal();
                donationType.setName(projectDataForm.get().donations.get(i));
                donationGoal.setAmount(Integer.parseInt(amount));
                donationGoal.setDonationType(donationType);
                donationGoalList.add(donationGoal);
                i++;
            }

            address.setCountry(projectDataForm.get().country);
            address.setCity(projectDataForm.get().city);
            address.setStreet(projectDataForm.get().street);

            project.setAddress(address);
            project.setDonationGoals(donationGoalList);
            projectService.saveProject(project);

            List<Project> projects = new Model.Finder<>(String.class, Project.class).all();
            return ok(views.html.index.render(projects));
        }
    }

    public static Result createProject() {
        return ok(newProject.render(Form.form(ProjectData.class)));
    }

}
