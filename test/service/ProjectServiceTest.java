package service;

import integration.DatabaseTest;
import models.*;
import org.junit.Test;
import services.ProjectService;
import viewmodels.ProjectDetail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProjectServiceTest {
    private User user;
    private List<Donation> donations = new ArrayList<>();

    public List<Project> getFilledDataStructure() {
        List<Project> projectList = new ArrayList<>();
        user = new User();
        user.save();

        DonationType type1 = new DonationType();
        type1.setName("Schuhe");
        type1.save();

        DonationType type2 = new DonationType();
        type2.setName("Hemden");
        type2.save();

        Project project1 = new Project();
        project1.save();

        DonationGoal goal1 = new DonationGoal();
        goal1.setAmount(10);
        goal1.setType(type1);
        goal1.setProject(project1);
        goal1.save();

        DonationGoal goal2 = new DonationGoal();
        goal2.setAmount(30);
        goal2.setType(type2);
        goal2.setProject(project1);
        goal2.save();

        Donation donation1 = new Donation();
        donation1.setAmount(5);
        donation1.setDate(new Date(5464));
        donation1.setDonationGoal(goal1);
        donation1.setUser(user);
        donation1.setMessageToCollector("123456");
        donation1.save();
        donations.add(donation1);

        Donation donation2 = new Donation();
        donation2.setAmount(10);
        donation2.setDate(new Date(5464));
        donation2.setDonationGoal(goal2);
        donation2.setUser(user);
        donation2.setMessageToCollector("123456");
        donation2.save();
        donations.add(donation2);

        Donation donation3 = new Donation();
        donation3.setAmount(5);
        donation3.setDate(new Date(5464));
        donation3.setDonationGoal(goal2);
        donation3.setUser(user);
        donation3.setMessageToCollector("123456");
        donation3.save();
        donations.add(donation3);

        Project project2 = new Project();
        project2.save();

        DonationGoal goal3 = new DonationGoal();
        goal3.setAmount(10);
        goal3.setType(type1);
        goal3.setProject(project2);
        goal3.save();

        Donation donation4 = new Donation();
        donation4.setAmount(12);
        donation4.setDate(new Date(5464));
        donation4.setDonationGoal(goal3);
        donation4.setUser(user);
        donation4.setMessageToCollector("123456");
        donation4.save();

        project1.refresh();
        project2.refresh();
        projectList.add(project1);
        projectList.add(project2);

        return projectList;
    }

    @Test
    public void testStateSmallerThanGoal() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            List<Project> projectList = getFilledDataStructure();
            int state1 = ProjectService.getStateOfProjectInPercent(projectList.get(0));
            assertEquals(50, state1);
        });
    }

    @Test
    public void testStateBiggerThanGoal() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            List<Project> projectList = getFilledDataStructure();
            int state1 = ProjectService.getStateOfProjectInPercent(projectList.get(1));
            assertEquals(100, state1);
        });
    }

    @Test
    public void testGetDonators() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            List<Project> projectList = getFilledDataStructure();
            Set<User> donators = ProjectService.getDonators(projectList.get(0));
            assertTrue(donators.contains(user));
        });
    }

    @Test
    public void testGetDonationsForUser() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            List<Project> projectList = getFilledDataStructure();
            ProjectDetail projectDetail = new ProjectDetail(projectList.get(0));
            List<Donation> donationsForUser = projectDetail.getDonationsForUser(user);
            assertTrue(donationsForUser.equals(donations));
        });
    }
}
