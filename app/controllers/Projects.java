package controllers;


import models.DonationGoal;
import models.DonationType;
import models.Project;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import controllers.forms.Contact;
import controllers.forms.Converter;
import controllers.forms.ProjectData;
import controllers.forms.Waren;
import views.html.newProject;

import java.util.ArrayList;
import java.util.List;



public class Projects extends Controller {

    public static Result getProjects() {
        List<Project> projects = new Model.Finder<>(String.class, Project.class).all();
        return ok(views.html.index.render(projects));
    }

    public static Result getProject(long id) {
        Model.Finder<Long, Project> finder = new Model.Finder<>(Long.class, Project.class);
        Project project = finder.byId(id);
        return ok(views.html.detail.render(project));
    }

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
            project.save();

            List<Project> projects = new Model.Finder<>(String.class, Project.class).all();
            return ok(views.html.index.render(projects));
        }
    }

    public static Result createProject() {
        return ok(newProject.render(Form.form(ProjectData.class)));
    }

}
