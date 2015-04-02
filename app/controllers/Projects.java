package controllers;


import models.DonationGoal;
import models.DonationType;
import models.Project;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import views.forms.Contact;
import views.forms.Converter;
import views.forms.ProjectData;
import views.forms.Waren;
import views.html.newProject;

import java.util.ArrayList;
import java.util.List;



public class Projects extends Controller {
    private static Form<Contact> contactForm = new Form<>(Contact.class);
    private static Form<Waren> warenForm = new Form<>(Waren.class);
    private static Form<ProjectData> projectDataForm = new Form<>(ProjectData.class);

    private static models.Project project = new models.Project();
    private static List<DonationGoal> donationGoalList = new ArrayList<>();
    private static models.Address address = new models.Address();
    private static Boolean[] state = new Boolean[3];

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
        projectDataForm = Form.form(ProjectData.class).bindFromRequest();
        if (projectDataForm.hasErrors()) {
            System.out.println("Projectdata has errors");
            changeState(0);
            return badRequest(newProject.render(projectDataForm,warenForm,contactForm,state));
        } else {
            project.setTitle(projectDataForm.get().title);
            project.setDescription(projectDataForm.get().description);
            project.setEndsAt(Converter.stringToSqlDate(projectDataForm.get().endsAt));
            project.setStartsAt(Converter.stringToSqlDate(projectDataForm.get().startsAt));
            System.out.println("ProjectData");
            changeState(1);
            return ok(newProject.render(projectDataForm, warenForm, contactForm,state));
        }
    }

    public static Result addWaren(){
        warenForm = Form.form(Waren.class).bindFromRequest();
        if (warenForm.hasErrors()) {
            System.out.println("Waren has errrors: ");
            changeState(1);
            return badRequest(newProject.render(projectDataForm,warenForm,contactForm,state));
        } else {
            DonationGoal donationGoal = new DonationGoal();
            DonationType donationType = new DonationType();
            donationGoal.setAmount(Integer.parseInt(warenForm.get().amount));
            donationType.setName(warenForm.get().donation);
            donationGoal.setDonationType(donationType);
            donationGoalList.add(donationGoal);
            System.out.println("Waren");
            changeState(2);
            return ok(newProject.render(projectDataForm, warenForm, contactForm, state));
        }
    }


    public static Result addContact(){
        contactForm = Form.form(Contact.class).bindFromRequest();
        if (contactForm.hasErrors()) {
            changeState(2);
            return badRequest(newProject.render(projectDataForm,warenForm,contactForm, state));
        } else {
            project.setContact(contactForm.get().contact);
            address.setCountry(contactForm.get().country);
            address.setCity(contactForm.get().city);
            address.setStreet(contactForm.get().street);
            System.out.println("Contact");
            changeState(3);
            return ok(newProject.render(projectDataForm,warenForm,contactForm, state));
        }
    }

    public static Result saveProject(){
        project.setDonationGoals(donationGoalList);
        project.setAddress(address);
        project.save();
        project = new models.Project(); //TODO: Fix this later
        System.out.println("Save");
        return redirect(routes.Application.index());
    }


    public static Result foo() {
        changeState(0);
        return ok(newProject.render(Form.form(ProjectData.class), Form.form(Waren.class), Form.form(Contact.class), state));
    }

    protected static boolean isPositivNumber(String number){
        return number.matches("[1-9]\\d*");
    }

    private static void changeState(int n){
        for(int i = 0; i < state.length; i++){
            if(i == n){
                state[i] = true;
            }else{
                state[i] = false;
            }
        }
    }
}
