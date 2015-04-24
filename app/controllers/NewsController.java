package controllers;

import exceptions.AfricaException;
import models.*;
import play.data.Form;
import play.mvc.Result;
import services.ProjectService;
import play.mvc.Security;
import viewmodels.NewsData;

import static play.mvc.Results.badRequest;

public class NewsController {

    @Security.Authenticated(AuthenticationController.class)
    public static Result addNews() throws AfricaException {
        Form<NewsData> newsDataForm = Form.form(NewsData.class).bindFromRequest();
        models.Project project = ProjectService.getProjectById(Long.valueOf(newsDataForm.get().projectId));
        if (newsDataForm.hasErrors()) {
            return badRequest(views.html.newNews.render(project, newsDataForm));
        } else {

            News news = new News();
            news.setTitle(newsDataForm.get().title);
            news.setDescription(newsDataForm.get().description);
            if(newsDataForm.get().imageUrl!=null)news.setImageURL(newsDataForm.get().imageUrl);
            news.setDate(new java.sql.Date(new java.util.Date().getTime()));
            news.setProject(project);
            news.save();
            return play.mvc.Controller.redirect(routes.ApplicationController.index());
        }
    }
}
