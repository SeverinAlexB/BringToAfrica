package application;

import integration.DatabaseTest;
import models.*;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import play.mvc.Http;
import play.mvc.Result;
import services.DonationGoalService;
import services.ProjectService;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;
import static org.junit.Assert.assertEquals;


public class NewsControllerTest {
    @Test
    public void getNewsTest() {
        DatabaseTest.runInCleanApp((browser -> {
            String email = "bob@gmail.com";
            News news = new News();
            news.setId(10l);
            news.setDescription("Test News");
            news.setDate(new java.sql.Date(new java.util.Date().getTime()));
            news.save();
            assertThat(News.find.findUnique() != null);

            Result result = callAction(
                    controllers.routes.ref.NewsController.getNews(10l),
                    fakeRequest().withSession("email", email));
            assertEquals(200, status(result));
        }));
    }

    @Test
    public void createNewsTest() {
        DatabaseTest.runInFilledApp((browser -> {
            String firstName = "Michael";
            String lastName = "Blocker";
            String email = "michael.blocher@msn.com";
            String password = "MeinPw5#";

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPasswordHashedSalted(hash);
            user.save();

            Map<String, String> map = new HashMap<>();
            map.put("projectId", "10");
            map.put("title", "Test News");
            map.put("description", "New News");
            map.put("imageUrl", "http://test.ch");

            assertEquals(News.find.all().size(), 0);
            Result result = callAction(
                    controllers.routes.ref.NewsController.addNews(),
                    fakeRequest().withSession("email", email).withFormUrlEncodedBody(map));
            assertEquals(303, status(result));
            assertEquals(1, News.find.all().size());
        }));
    }
}
