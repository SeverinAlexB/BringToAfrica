package viewmodels;

import controllers.AfricaException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {

    public static java.sql.Date stringToSqlDate(String date) throws AfricaException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateUtil;
        try {
            dateUtil = sdf.parse(date);
        } catch (ParseException ex) {
            throw new AfricaException("stringToSqlDate()", ex);
        }
        java.sql.Date sqlStartDate = new Date(dateUtil.getTime());
        return sqlStartDate;
    }
}
