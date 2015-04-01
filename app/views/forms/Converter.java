package views.forms;

import controllers.AfricaException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Converter {

    public static java.sql.Date stringToSqlDate(String date)throws AfricaException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateUtil;
        try {
            dateUtil = sdf1.parse(date);
        }catch (  ParseException ex) {
            throw new AfricaException("stringToSqlDate()",ex);
        }
        java.sql.Date sqlStartDate = new Date(dateUtil.getTime());
        return sqlStartDate;
    }
}
