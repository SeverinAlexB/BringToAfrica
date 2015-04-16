package controllers;

import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import exceptions.AfricaException;
import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.DonationTypeService;
import viewmodels.DateConverter;
import play.mvc.Security;
import viewmodels.ProjectData;
import viewmodels.ProjectWidget;
import views.html.newProject;

import java.util.ArrayList;
import java.util.List;
import services.ProjectService;
import services.ConsumerService;


public class ProjectController extends Controller {


    public static Result getProjects() {
        PagingList<Project> pagingList = ProjectService.getPageingListOfProjects(10);
        pagingList.getFutureRowCount();
        Page<Project> page = pagingList.getPage(0);
        List<Project> projects = page.getList();
        List<ProjectWidget> widgets = new ArrayList<>();

        for(Project p :projects) {
            widgets.add(new ProjectWidget(p));
        }
        return ok(views.html.index.render(widgets));
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
                DonationType donationType = DonationTypeService.getOrSetDonationType(projectDataForm.get().donations.get(i));
                DonationGoal donationGoal = new DonationGoal();
                donationGoal.setAmount(Integer.parseInt(projectDataForm.get().amounts.get(i)));
                //donationGoal.setDonationType(donationType);
                donationType.addDonationGoal(donationGoal);
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
