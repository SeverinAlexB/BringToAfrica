package integration;

import models.*;
import org.junit.Test;


import java.sql.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Severin on 17.04.2015.
 */
public class ModelTest {
    private Address addAddress() {
        Address a = new Address();
        a.setCountry("Holland");
        a.setCity("Kopenhagen");
        a.save();
        return a;
    }
    private User addUser() {
        User user = new User();
        user.setFirstName("Marco");
        user.setLastName("Willi");
        user.setEmail("m@w.ch");
        user.save();
        return user;
    }
    private DonationType addType() {
        DonationType type = new DonationType();
        type.setName("Schuhe");
        type.save();
        return type;
    }
    private Project addProject(){
        Project project = new Project();
        project.setTitle("Test Projekt");
        project.setDescription("Beschreiba asdf adsf ");
        project.setStartsAt(new Date(365456));
        project.setEndsAt(new Date(365457));
        project.setImageURL("http://www.bluewin.ch");
        project.setContact("asfdasdfasdfasdf adsf");
        project.save();
        return project;
    }
    private DonationGoal addGoal() {
        DonationGoal goal = new DonationGoal();
        goal.setAmount(10);
        goal.save();
        return goal;
    }
    private Donation addDonation() {
        Donation d = new Donation();
        d.setDate(new Date(454665));
        d.setAmount(10);
        d.setMessageToCollector("asdfasdfadsfads");
        d.save();
        return d;
    }
    private News addNews() {
        News news = new News();
        news.setDate(new Date(561456));
        news.setTitle("adfsasdfa");
        news.setDescription("asdfasdf");
        news.setImageURL("http://www.bluewin.ch");
        news.save();
        return news;
    }
    @Test
    public void addUserTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            addUser();
            assertEquals(User.find.all().size(), 1);
        });

    }
    @Test
    public void addDonationTypeTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            addType();
            assertEquals(DonationType.find.all().size(), 1);
        });
    }
    @Test
    public void addAddressTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            addAddress();
            assertEquals(Address.find.all().size(), 1);
        });
    }
    @Test
    public void addProjectTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            addProject();
            assertEquals(Project.find.all().size(), 1);
        });

    }
    @Test
    public void addGoalTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            addGoal();
            assertEquals(DonationGoal.find.all().size(), 1);
        });

    }
    @Test
    public void addDonationTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            addDonation();
            assertEquals(Donation.find.all().size(), 1);
        });
    }
    @Test
    public void addNewsTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            addNews();
            assertEquals(News.find.all().size(), 1);
        });
    }
    @Test
    public void ProjectOwnerTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            User user = addUser();
            Project project = addProject();

            project.setOwner(user);
            project.save();
            user.refresh();

            assertEquals(1, user.getMyProjects().size());
        });
    }
    @Test
    public void ProjectAddressTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            Address address = addAddress();
            Project project = addProject();
            project.setAddress(address);
            project.save();
            project = Project.find.findUnique();
            assertEquals(address, project.getAddress());
        });
    }
    @Test
    public void DonationTypGoalTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            DonationType type = addType();
            DonationGoal goal = addGoal();
            goal.setType(type);
            goal.save();
            type.refresh();

            assertEquals(1, type.getDonationGoals().size());
        });
    }
    @Test
    public void ProjectGoalTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            DonationGoal goal = addGoal();
            Project project = addProject();
            goal.setProject(project);
            goal.save();
            project.refresh();

            assertEquals(1,project.getDonationGoals().size());
        });
    }
    @Test
    public void ProjectNewsTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            Project project = addProject();
            News news = addNews();

            news.setProject(project);
            news.save();
            project.refresh();

            assertEquals(1, project.getNews().size());
        });
    }
    @Test
    public void DonationUserTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            Donation donation = addDonation();
            User user = addUser();
            donation.setUser(user);
            donation.save();
            user.refresh();
            assertEquals(1, user.getDonations().size());
        });
    }
    @Test
    public void DonationDonationGoalTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            Donation donation = addDonation();
            DonationGoal goal = addGoal();
            donation.setDonationGoal(goal);
            donation.save();
            goal.refresh();

            assertEquals(1, goal.getDonations().size());
        });
    }
}
