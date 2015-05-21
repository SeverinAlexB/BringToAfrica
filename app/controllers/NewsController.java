package controllers;

import models.News;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import services.ProjectService;
import viewmodels.NewsData;

import static play.mvc.Results.*;

public class NewsController {

    @Security.Authenticated(AuthenticationController.class)
    public static Result addNews() {
        Form<NewsData> newsDataForm = Form.form(NewsData.class).bindFromRequest();
        models.Project project = ProjectService.getProjectById(Long.valueOf(newsDataForm.data().get("projectId")));
        if (newsDataForm.hasErrors()) {
            return badRequest(views.html.project.news.newNews.render(project, newsDataForm));
        } else {
            News news = new News();
            news.setTitle(newsDataForm.get().title);
            news.setDescription(newsDataForm.get().description);
            if(newsDataForm.get().imageUrl!=null)news.setImageURL(newsDataForm.get().imageUrl);
            news.setDate(new java.sql.Date(new java.util.Date().getTime()));
            news.setProject(project);
            news.save();
            return redirect(routes.ProjectController.getProject(project.getId()));
        }
    }

    public static Result getNews(Long id) {
        News news = News.find.byId(id);
        news.refresh();
        return ok(views.html.project.news.detail.render(news));
    }
}
