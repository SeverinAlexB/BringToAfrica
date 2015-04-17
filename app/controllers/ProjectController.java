package controllers;

import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;
import exceptions.AfricaException;
import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;
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

    private static int PAGE_SIZE = 10;

    public static Result getProjectWidgets(int page) {
        Page<Project> projectPage = ProjectService.getProjectPage(PAGE_SIZE,page);
        if(projectPage == null){
            System.out.println("null");
            return badRequest();
        }else{
            List<ProjectWidget> widgets = new ArrayList<>();
            for(Project p :projectPage.getList()) {
                widgets.add(new ProjectWidget(p));
            }
            return ok(views.html.index.render(widgets, projectPage.getTotalPageCount(), page));
        }

    }

    public static Html getProjectWidget(long id) {
        Project project = ProjectService.getProjectById(id);
        ProjectWidget projectWidget = new ProjectWidget(project);
        return views.html.ProjectManagement.widget.render(projectWidget);
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
                DonationGoal donationGoal = new DonationGoal(project);
                donationGoal.setAmount(Integer.parseInt(projectDataForm.get().amounts.get(i)));
                donationType.addDonationGoal(donationGoal);
            }

            address.setCountry(projectDataForm.get().country);
            address.setCity(projectDataForm.get().city);
            project.setAddress(address);
            user.addProject(project);
            user.save();
            return redirect(routes.ProjectController.getProjectWidgets(0));
        }
    }

    @Security.Authenticated(AuthenticationController.class)
    public static Result createProject() {
        return ok(newProject.render(Form.form(ProjectData.class)));
    }

}
