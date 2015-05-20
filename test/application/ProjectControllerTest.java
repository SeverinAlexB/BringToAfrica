package application;

import controllers.ProjectController;
import org.junit.Assert;
import org.junit.Test;
import services.DateConverter;
import viewmodels.ProjectData;

public class ProjectControllerTest extends ProjectController {


    @Test
    public void isDateTest() {
        String date1 = "09.05.2015";
        String date2 = "19.12.1991";
        Assert.assertTrue(ProjectData.isDate(date1));
        Assert.assertTrue(ProjectData.isDate(date2));
    }

    @Test
    public void isNotDateTest(){
        String date3 = "2015-0e-09";
        String date4 = "31-02-1969";
        Assert.assertFalse(ProjectData.isDate(date3));
        Assert.assertFalse(ProjectData.isDate(date4));
    }

}
