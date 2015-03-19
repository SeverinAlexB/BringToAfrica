package controllers;

import models.Person;
import models.Project;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result addProject() {
        Project project = Form.form(Project.class).bindFromRequest().get();
        project.save();
        return redirect(routes.Application.index());
    }

    public static Result getProjects() {
        List<Project> projects = new Model.Finder(String.class, Project.class).all();
        return ok(toJson(projects));
    }
}
