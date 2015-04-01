package Application;

import controllers.Projects;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.fest.assertions.Assertions.assertThat;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ProjectControllerTest extends Projects {


    @Test
    public void isDateTest() {
        String date1 = "2015-04-09";
        String date2 = "1999-01-31";
        Assert.assertTrue(Projects.isDate(date1));
        Assert.assertTrue(Projects.isDate(date2));

        String date3 = "2015-0e-09";
        String date4 = "31.02.1969";
        Assert.assertFalse(Projects.isDate(date3));
        Assert.assertFalse(Projects.isDate(date4));
    }
    @Test
    public void stringToSqlDateTest() throws Exception {
        String date = "2015-04-09";
        java.sql.Date expected = java.sql.Date.valueOf("2015-04-09");
        java.sql.Date result = Projects.stringToSqlDate(date);
        Assert.assertTrue(result.equals(expected));
    }

    @Test(expected=Exception.class)
    public void stringToSqlDateExceptionTest() throws Exception {
        String date1 = "09.04.2015";
        Projects.stringToSqlDate(date1);
    }
    @Test
    public void isPositiveNumberTest() {
        String num1 = "200";
        String num2 = "9";
        String num3 = "20";

        Assert.assertTrue(Projects.isPositivNumber(num1));
        Assert.assertTrue(Projects.isPositivNumber(num2));
        Assert.assertTrue(Projects.isPositivNumber(num3));

        String num4 = "0";
        String num5 = "-1";
        String num6 = "-100";
        String num7 = "a";
        String num8 = "*ç%";
        Assert.assertFalse(Projects.isPositivNumber(num4));
        Assert.assertFalse(Projects.isPositivNumber(num5));
        Assert.assertFalse(Projects.isPositivNumber(num6));
        Assert.assertFalse(Projects.isPositivNumber(num7));
        Assert.assertFalse(Projects.isPositivNumber(num8));
    }
}
