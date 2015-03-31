package controllers;


import models.DonationGoal;
import models.DonationType;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.newProject;
import play.data.Form;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import patch.PatchedForm;



public class Project extends Controller {

    //private static Form<ProjectData> projectDataForm = new PatchedForm<ProjectData>(ProjectData.class);
    //private static Form<Waren> warenForm = new PatchedForm<Waren>(Waren.class).bindFromRequest();
    //private static Form<Contact> contactForm = new PatchedForm<Contact>(Contact.class);
    private static Form<Contact> contactForm = new Form<Contact>(Contact.class);
    private static Form<Waren> warenForm = new Form<Waren>(Waren.class);
    private static Form<ProjectData> projectDataForm = new Form<ProjectData>(ProjectData.class);

    private static models.Project project = new models.Project();
    private static List<DonationGoal> donationGoalList = new ArrayList<>();
    private static models.Address address = new models.Address();

    public static class ProjectData{
        @Constraints.Required
        public String title;
        @Constraints.Required
        public String description;
        @Constraints.Required
        public String endsAt;
        @Constraints.Required
        public String startsAt;

        public String validate() {
            System.out.println("ProjectData Validation");
            if (title.isEmpty()) {
                return "Title is empty";
            }else if(description.isEmpty()){
                return "Description is empty";
            }else if(endsAt.isEmpty()){
                return "endsAt is empty";
            }else if(startsAt.isEmpty()){
                return "startsAt is empty";
            }
            return null;
        }
    }
    public static class Contact{
        @Constraints.Required
        public String contact;
        @Constraints.Required
        public String destination;

        public String validate() {
            System.out.println("Contact Validation");
            if (contact.isEmpty()) {
                return "contact is empty";
            }else if(destination.isEmpty()){
                return "destination is empty";
            }
            return null;
        }
    }

    public static class Waren{
        @Constraints.Required
        public String amount;
        @Constraints.Required
        public String donation;

        public String validate() {
            System.out.println("Waren Validation");
            if (amount.isEmpty()) {
                return "amount is empty";
            }else if(donation.isEmpty()){
                return "donation is empty";
            }
            return null;
        }
    }

    //TODO: https://gist.github.com/ndeverge/3074629
    public static Result addProjectData() throws Exception{
        //Form<NewProject> projectForm = Form.form(NewProject.class).bindFromRequest();
        //Form<ProjectData> projectDataForm = new PatchedForm<ProjectData>(ProjectData.class).bindFromRequest();
        projectDataForm = Form.form(ProjectData.class).bindFromRequest();
        if (projectDataForm.hasErrors()) {
            System.out.println("Projectdata has errors");
            return badRequest(newProject.render(projectDataForm,warenForm,contactForm));
        } else {
            project.setTitle(projectDataForm.get().title);
            project.setDescription(projectDataForm.get().description);
            project.setEndsAt(stringToSqlDate(projectDataForm.get().endsAt));
            project.setStartsAt(stringToSqlDate(projectDataForm.get().startsAt));
            System.out.println("ProjectData");
            return redirect("/projects/new#billing");
        }
    }

    public static Result addWaren() throws Exception{
        warenForm = Form.form(Waren.class).bindFromRequest();
        if (warenForm.hasErrors()) {
            System.out.println("Waren has errrors: ");
            return badRequest(newProject.render(projectDataForm,warenForm,contactForm));
        } else {
            DonationGoal donationGoal = new DonationGoal();
            DonationType donationType = new DonationType();
            donationGoal.setAmount(Integer.parseInt(warenForm.get().amount));
            donationType.setName(warenForm.get().donation);
            donationGoal.setDonationType(donationType);
            donationGoalList.add(donationGoal);
            System.out.println("Waren");
            return redirect("/projects/new#review");
        }
    }


    public static Result addContact() throws Exception{
        contactForm = Form.form(Contact.class).bindFromRequest();
        if (contactForm.hasErrors()) {
            for(play.data.validation.ValidationError err: contactForm.globalErrors()){
                System.out.println(err.messages());
            }
            System.out.println("Contact has errors");
            return badRequest(newProject.render(projectDataForm,warenForm,contactForm));
        } else {
            project.setContact(contactForm.get().contact);
            address.setCountry(contactForm.get().destination);
            System.out.println("Contact");
            return redirect("/projects/new#confirmation");
        }
    }

    public static Result saveProject() throws Exception{
        project.setDonationGoals(donationGoalList);
        project.setAddress(address);
        address.setProject(project);
        for(DonationGoal dg: donationGoalList){
            dg.setProject(project);
            dg.save();
        }
        address.save();
        project.save();
        System.out.println("Save");
        return redirect(routes.Application.index());
    }


    public static Result foo() {
        return ok(newProject.render(Form.form(ProjectData.class), Form.form(Waren.class), Form.form(Contact.class)));
    }

    private static java.sql.Date stringToSqlDate(String date)throws Exception{
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateUtil;
        try {
            dateUtil = sdf1.parse(date);
        }catch (  ParseException ex) {
            throw new Exception("stringToSqlDate()",ex);
        }
        java.sql.Date sqlStartDate = new Date(dateUtil.getTime());
        return sqlStartDate;
    }
}
