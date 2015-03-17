package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import models.Person;
import models.Project;

import play.data.Form;

import java.util.List;

import play.db.ebean.Model;

import static play.libs.Json.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result addPerson() {
    	Person person = Form.form(Person.class).bindFromRequest().get();
    	person.save();
    	return redirect(routes.Application.index());
    }

    public static Result getPersons() {
    	List<Person> persons = new Model.Finder(String.class, Person.class).all();
    	return ok(toJson(persons));
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
