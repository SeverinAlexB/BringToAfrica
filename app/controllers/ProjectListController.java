package controllers;

import com.avaje.ebean.Page;
import models.Project;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;
import services.ProjectService;
import viewmodels.ProjectWidget;

import java.util.ArrayList;
import java.util.List;

import static play.mvc.Results.ok;

/**
 * Created by Severin on 16.05.2015.
 */
public class ProjectListController extends Controller {
    private static final int PAGE_SIZE = 2;
    public static Result getProjectWidgets(int page) {
        return ok(getProjectWidgetList(page));
    }
    public static Html getProjectWidgetList(int page) {
        Page<Project> projectPage = ProjectService.getProjectPage(PAGE_SIZE, page);
        List<ProjectWidget> widgets = new ArrayList<>();
        for (Project p :projectPage.getList()) {
            widgets.add(new ProjectWidget(p));
        }
        return views.html.project.list.projectList.render(widgets, projectPage.getTotalPageCount(), page);
    }
}
