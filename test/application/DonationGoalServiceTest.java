package application;

import integration.DatabaseTest;
import models.*;
import org.junit.Test;
import services.DonationGoalService;
import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class DonationGoalServiceTest {

    public Project getFilledDataStructure() {
        User user = new User();
        user.save();

        DonationType type1 = new DonationType();
        type1.setName("Schuhe");
        type1.save();

        DonationType type2 = new DonationType();
        type2.setName("Hemden");
        type2.save();

        Project project = new Project();
        project.save();

        DonationGoal goal1 = new DonationGoal();
        goal1.setAmount(10);
        goal1.setType(type1);
        goal1.setProject(project);
        goal1.save();

        DonationGoal goal2 = new DonationGoal();
        goal2.setAmount(20);
        goal2.setType(type2);
        goal2.setProject(project);
        goal2.save();

        Donation donation1 = new Donation();
        donation1.setAmount(5);
        donation1.setDate(new Date(5464));
        donation1.setDonationGoal(goal1);
        donation1.setUser(user);
        donation1.setMessageToCollector("123456");
        donation1.save();

        Donation donation2 = new Donation();
        donation2.setAmount(10);
        donation2.setDate(new Date(5464));
        donation2.setDonationGoal(goal2);
        donation2.setUser(user);
        donation2.setMessageToCollector("123456");
        donation2.save();

        Donation donation3 = new Donation();
        donation3.setAmount(20);
        donation3.setDate(new Date(5464));
        donation3.setDonationGoal(goal2);
        donation3.setUser(user);
        donation3.setMessageToCollector("123456");
        donation3.save();

        project.refresh();

        return project;
    }
    @Test
    public void testGetStateWithSingleDonation() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            Project project = getFilledDataStructure();
            int state1 = DonationGoalService.getState(project.getDonationGoals().get(0));
            assertEquals(50, state1);
        });
    }

    @Test
    public void testGetStateWithMultipleDonations() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            Project project = getFilledDataStructure();
            int state2 = DonationGoalService.getState(project.getDonationGoals().get(1));
            assertEquals(100, state2);
        });
    }
}
