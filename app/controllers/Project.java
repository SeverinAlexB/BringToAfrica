package controllers;

import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.newProject;

public class Project extends Controller {
        public static Result addProject() {
 /*           DynamicForm requestData = Form.form().bindFromRequest();
            String firstname = requestData.get("firstname");
            String lastname = requestData.get("lastname");
            return ok("Hello " + firstname + " " + lastname);

*/
        models.Project project = Form.form(models.Project.class).bindFromRequest().get();
        project.save();
        return redirect(routes.Application.index());
    }

    public static Result foo() {
        return ok(newProject.render());
    }
}
