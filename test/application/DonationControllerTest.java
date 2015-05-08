package application;

import controllers.DonationController;
import integration.DatabaseTest;
import models.*;
import org.junit.Test;
import play.Play;
import play.data.Form;
import viewmodels.donation.CreateDonationData;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class DonationControllerTest extends DonationController {
    private Form<CreateDonationData> getCreateDonationDataForm(CreateDonationData data) {
        Form<CreateDonationData> form = new Form<CreateDonationData>(CreateDonationData.class);
        form = form.fill(data);
        return form;
    }

    public Form<CreateDonationData> getFilledForm() {
        CreateDonationData data = new CreateDonationData();
        data.remarks = "blabla";
        data.projectId = 1;
        data.donations.add("Schuhe");
        data.donations.add("Hose");
        data.amounts.add(1);
        data.amounts.add(5);
        return getCreateDonationDataForm(data);
    }
    public Form<CreateDonationData> getFilledFormZero() {
        CreateDonationData data = new CreateDonationData();
        data.remarks = "blabla";
        data.projectId = 1;
        data.donations.add("Schuhe");
        data.donations.add("Hose");
        data.amounts.add(0);
        data.amounts.add(5);
        return getCreateDonationDataForm(data);
    }



    @Test
    public void createDonationsTest(){
        DatabaseTest.runInFilledApp("testFiles/DonationControllerTest.yml", testBrowser -> {
            Project project = Project.find.byId(1l);
            User user = User.find.byId(1l);
            for (DonationGoal goal : project.getDonationGoals()) {
                assertEquals("testfile wrong", 0, goal.getDonations().size());
            }
            Form<CreateDonationData> form = getFilledForm();
            DonationController.createDonations(form, user);

            project = Project.find.byId(1l);
            for (DonationGoal goal : project.getDonationGoals()) {
                assertEquals("1 donation created", 1, goal.getDonations().size());
            }

        });
    }
    @Test
    public void createDonationsZeroTest(){
        DatabaseTest.runInFilledApp("testFiles/DonationControllerTest.yml", testBrowser -> {
            Project project =  Project.find.byId(1l);
            User user = User.find.byId(1l);
            for(DonationGoal goal: project.getDonationGoals()){
                assertEquals("testfile wrong", 0, goal.getDonations().size());
            }
            Form<CreateDonationData> form = getFilledFormZero();
            DonationController.createDonations(form, user);

            project =  Project.find.byId(1l);
            assertEquals("1 donation created", 1, project.getDonationGoals().get(1).getDonations().size());
            assertEquals("0 donation created", 0, project.getDonationGoals().get(0).getDonations().size());

        });
    }
    @Test
    public void getGoalByTypeTest() {
        DatabaseTest.runInFilledApp("testFiles/DonationControllerTest.yml", testBrowser -> {
            Project project = Project.find.byId(1l);
            DonationGoal found = getGoalByType(project.getDonationGoals(),"Schuhe");
            assertEquals(project.getDonationGoals().get(0),found);

        });
    }
    @Test
    public void createDonationTest() {
        DatabaseTest.runInFilledApp("testFiles/DonationControllerTest.yml", testBrowser -> {
            User user = User.find.byId(1l);
            Project project = Project.find.byId(1l);
            DonationGoal goal = project.getDonationGoals().get(0);
            int amount = 16;
            String msgToCollector = "Hey Junge!";

            Donation donation = createDonation(user,goal,amount,msgToCollector);
            donation = Donation.find.byId(donation.getId());

            assertEquals(user.getId(),donation.getUser().getId());
            assertEquals(goal.getId(),donation.getDonationGoal().getId());
            assertEquals(amount,donation.getAmount());
            assertEquals(msgToCollector,donation.getMessageToCollector());
            assertNotNull(donation.getDate());

        });
    }
}
