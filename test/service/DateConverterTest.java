package service;

import org.junit.Assert;
import org.junit.Test;
import services.DateConverter;

public class DateConverterTest {

    @Test
    public void stringToSqlDateTest() throws Exception {
        String date = "09.04.2015";
        java.sql.Date expected = java.sql.Date.valueOf("2015-04-09");
        java.sql.Date result = DateConverter.stringToSqlDate(date);
        Assert.assertTrue(result.equals(expected));
    }

    @Test(expected=Exception.class)
    public void stringToSqlDateExceptionTest() throws Exception {
        String date1 = "09-04-2015";
        DateConverter.stringToSqlDate(date1);
    }
}
