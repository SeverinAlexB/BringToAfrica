package controllers;

import exceptions.AfricaException;
import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import viewmodels.DateConverter;
import play.mvc.Security;
import viewmodels.ProjectData;
import views.html.newProject;

import java.util.List;
import services.ProjectService;
import services.ConsumerService;


public class ProjectController extends Controller {


    public static Result getProjects() {
        List<Project> projects = ProjectService.getAllProjects();
        return ok(views.html.index.render(projects));
    }

    public static Result getProject(long id) {
        Project project = ProjectService.getProjectById(id);
        return ok(views.html.detail.render(project));
    }

    @Security.Authenticated(AuthenticationController.class)
    public static Result addProjectData() throws AfricaException {
        Form<ProjectData> projectDataForm = Form.form(ProjectData.class).bindFromRequest();
        if (projectDataForm.hasErrors()) {
            return badRequest(newProject.render(projectDataForm));
        } else {
            User user = ConsumerService.getConsumerByEmail(request().username());
            models.Project project = new models.Project();
            Address address = new models.Address();
            project.setTitle(projectDataForm.get().title);
            project.setDescription(projectDataForm.get().description);
            project.setImageURL(projectDataForm.get().imageURL);
            project.setEndsAt(DateConverter.stringToSqlDate(projectDataForm.get().endsAt));
            project.setStartsAt(DateConverter.stringToSqlDate(projectDataForm.get().startsAt));
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
            user.addProject(project);
            user.save();
            return redirect(routes.ProjectController.getProjects());
        }
    }

    @Security.Authenticated(AuthenticationController.class)
    public static Result createProject() {
        return ok(newProject.render(Form.form(ProjectData.class)));
    }

}
