package controllers;


import models.Project;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.helper.form;
import views.html.newProject;
import play.data.Form;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import patch.PatchedForm;



public class Projects extends Controller {

    public static class NewProject {
        @Constraints.Required
        public String title;
        @Constraints.Required
        public String description;
        @Constraints.Required
        public String contact;
        @Constraints.Required
        public String endsAt;
        @Constraints.Required
        public String startsAt;

        public String validate() {
            if (title.isEmpty()) {
                return "Title is empty";
            }else if(description.isEmpty()){
                return "Description is empty";
            }else if(contact.isEmpty()){
                return "Contact is empty";
            }else if(endsAt.isEmpty()){
                return "endsAt is empty";
            }else if(startsAt.isEmpty()){
                return "startsAt is empty";
            }
            return null;
        }
    }

    public static Result getProjects() {
        List<Project> projects = new Model.Finder(String.class, Project.class).all();
        return ok(views.html.index.render(projects));
    }

    public static Result getProject(long id) {
        Model.Finder<Long, Project> finder = new Model.Finder(String.class, Project.class);
        Project project = finder.byId(id);
        return ok(views.html.detail.render(project));
    }

    //TODO: https://gist.github.com/ndeverge/3074629
    public static Result addProject() throws Exception{
        //Form<NewProject> projectForm = Form.form(NewProject.class).bindFromRequest();
        Form<NewProject> projectForm = new PatchedForm<NewProject>(NewProject.class).bindFromRequest();
        try{
            System.out.println(projectForm.get().title);
        }catch (Exception e){
            System.out.println(e);
        }
        if (projectForm.hasErrors()) {
            return badRequest(newProject.render(projectForm));
        } else {
            String title = projectForm.get().title;
            System.out.println("title " + title);
            String description = projectForm.get().description;
            java.sql.Date endsAt = stringToSqlDate(projectForm.get().endsAt);
            java.sql.Date startsAt = stringToSqlDate(projectForm.get().startsAt);
            String contact = projectForm.get().contact;
            models.Project project = new models.Project();
            project.setTitle(title);
            project.setDescription(description);
            project.setEndsAt(endsAt);
            project.setStartsAt(startsAt);
            project.setContact(contact);
            project.save();
            return redirect(routes.Application.index());
        }
    }

    public static Result foo() {
        return ok(newProject.render(Form.form(NewProject.class)));
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
