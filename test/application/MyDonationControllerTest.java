package application;

import integration.DatabaseTest;
import controllers.MyDonationsController;
import models.*;
import org.junit.Test;
import viewmodels.donation.MyDonationsData;
import viewmodels.donation.ProjectDonationData;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;


public class MyDonationControllerTest extends MyDonationsController {
    public User getFilledDataStructure() {
        User user = new User();
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
        goal2.setAmount(11);
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

        Donation donation2 = new Donation();
        donation2.setAmount(5);
        donation2.setDate(new Date(5464));
        donation2.setDonationGoal(goal2);
        donation2.setUser(user);
        donation2.setMessageToCollector("123456");
        donation2.save();


        Project project2 = new Project();
        project2.save();

        DonationGoal goal3 = new DonationGoal();
        goal3.setAmount(10);
        goal3.setType(type1);
        goal3.setProject(project2);
        goal3.save();

        Donation donation3 = new Donation();
        donation3.setAmount(5);
        donation3.setDate(new Date(5464));
        donation3.setDonationGoal(goal3);
        donation3.setUser(user);
        donation3.setMessageToCollector("123456");
        donation3.save();

        user.refresh();

        return user;
    }
    @Test
    public void getFormDataTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            User user = getFilledDataStructure();
            MyDonationsData data = MyDonationControllerTest.getFormData(user);

            ProjectDonationData actual = data.donations.get(0);
            Project expectedProject = user.getDonations().get(0).getDonationGoal().getProject();
            Donation expectedDonation = user.getDonations().get(0);
            String expectedDate = new SimpleDateFormat("dd.MM.yyyy").format(expectedDonation.getDate());

            assertEquals("2 project",2,data.donations.size());
            assertEquals(expectedProject.getId(), actual.project.getId());
            assertEquals(expectedProject.getContact(),actual.contact);
            assertEquals(expectedDonation.getMessageToCollector(),actual.notice);
            assertEquals(expectedDate,actual.date);
            assertEquals(2,data.donations.get(0).donations.size());
            assertEquals(expectedDonation.getAmount(), actual.donations.get(0).amount);
            assertEquals(expectedDonation.getDonationGoal().getType().getName(), actual.donations.get(0).type);
        });


    }
    @Test
    public void getFormDataNullTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            User user = new User();
            user.save();
            MyDonationsData data = MyDonationControllerTest.getFormData(user);
            assertEquals(0,data.donations.size());
        });


    }
}
