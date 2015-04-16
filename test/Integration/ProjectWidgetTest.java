package Integration;

import models.Donation;
import models.Project;
import models.User;
import org.junit.Test;
import org.openqa.selenium.By;
import play.mvc.Result;
import services.ProjectService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ProjectWidgetTest {

    @Test
    public void WidgetTest(){
        DatabaseTest.runInFilledApp((browser -> {
            assertTrue(Project.find.all().size() == 1);
            assertTrue(Donation.find.all().size() == 1);
            int state = ProjectService.getStateOfProjectInPercent(Project.find.findUnique().getId());
            assertEquals(5, state);
        }));
    }


}
