package controllers;

import models.Project;
import play.data.DynamicForm;
import play.data.Form;
import play.db.ebean.Model;
import play.libs.Comet;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import javax.persistence.criteria.From;
import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }



    public static Result getProjects() {
        List<Project> projects = new Model.Finder(String.class, Project.class).all();
        return ok(toJson(projects));
    }
}
