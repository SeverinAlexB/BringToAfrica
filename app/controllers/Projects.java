package controllers;


import models.*;
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
import services.ConsumerService;


public class Projects extends Controller {


    public static Result getProjects() {
        List<Project> projects = ProjectService.getAllProjects();
        return ok(views.html.index.render(projects));
    }

    public static Result getProject(long id) {
        Project project = ProjectService.getProjectById(id);
        return ok(views.html.detail.render(project));
    }

    @Security.Authenticated(Secured.class)
    public static Result addProjectData() throws  AfricaException{
        Form<ProjectData> projectDataForm = Form.form(ProjectData.class).bindFromRequest();
        if (projectDataForm.hasErrors()) {
            return badRequest(newProject.render(projectDataForm));
        } else {
            Consumer consumer = ConsumerService.getConsumerByEmail(request().username());
            if(consumer != null) {
                models.Project project = new models.Project();
                Address address = new models.Address();

                project.setTitle(projectDataForm.get().title);
                project.setDescription(projectDataForm.get().description);
                project.setImageURL(projectDataForm.get().imageURL);
                project.setEndsAt(Converter.stringToSqlDate(projectDataForm.get().endsAt));
                project.setStartsAt(Converter.stringToSqlDate(projectDataForm.get().startsAt));
                project.setContact(projectDataForm.get().contactInformation);

                for (int i = 0; i < projectDataForm.get().amounts.size(); i++) {
                    DonationType donationType = new DonationType();
                    DonationGoal donationGoal = new DonationGoal();
                    donationType.setName(projectDataForm.get().donations.get(i));
                    donationGoal.setAmount(Integer.parseInt(projectDataForm.get().amounts.get(i)));
                    donationGoal.setDonationType(donationType);
                    project.addDonationGoal(donationGoal);
                }

                address.setCountry(projectDataForm.get().country);
                address.setCity(projectDataForm.get().city);

                project.setAddress(address);
                consumer.addProject(project);
                consumer.save();

                return redirect(routes.Projects.getProjects());
            }else{
                projectDataForm.reject("Benutzer nicht gefunden.");
                return badRequest(newProject.render(projectDataForm));
            }
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result createProject() {
        return ok(newProject.render(Form.form(ProjectData.class)));
    }

}
