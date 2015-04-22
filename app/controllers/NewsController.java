package controllers;

import exceptions.AfricaException;
import models.News;
import play.mvc.Controller;
import play.twirl.api.Html;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import services.ProjectService;
import viewmodels.NewsData;
public class NewsController {

    @Security.Authenticated(AuthenticationController.class)
    public static Result addNewsToProject(long id) throws AfricaException {
        Form<NewsData> newsDataForm = Form.form(NewsData.class).bindFromRequest();
        if (newsDataForm.hasErrors()) {
            return badRequest(views.html.newNews.render(newsDataForm));
        } else {
            models.Project project = ProjectService.getProjectById(id);
            News news = new News();
            news.setTitle(newsDataForm.get().title);
            news.setDescription(newsDataForm.get().description);
            if(newsDataForm.get().imageUrl!=null)news.setImageURL(newsDataForm.get().imageUrl);
            news.setDate(new java.sql.Date(new java.util.Date().getTime()));
            news.setProject(project);
            news.save();
            return redirect(routes.ApplicationController.index());
        }
    }
}
