package controllers;

import exceptions.AfricaException;
import models.*;
import play.data.Form;
import play.mvc.Result;
import services.ProjectService;
import play.mvc.Security;
import viewmodels.NewsData;
import viewmodels.ProjectDetail;
import viewmodels.ProjectWidget;
import viewmodels.donation.CreateDonationData;

import java.util.List;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;

public class NewsController {

    @Security.Authenticated(AuthenticationController.class)
    public static Result addNews() throws AfricaException {
        Form<NewsData> newsDataForm = Form.form(NewsData.class).bindFromRequest();
        models.Project project = ProjectService.getProjectById(Long.valueOf(newsDataForm.get().projectId));
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
        return ok(views.html.project.news.detail.render(news));
    }

    private static Form<CreateDonationData> createDonationForm(Project project) {
        List<DonationGoal> goals = project.getDonationGoals();
        CreateDonationData data = new CreateDonationData(goals);
        return Form.form(CreateDonationData.class).fill(data);
    }

}
