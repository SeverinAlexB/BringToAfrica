package views.forms;

import controllers.AfricaException;
import play.data.validation.Constraints;

import static views.forms.Converter.stringToSqlDate;

public class ProjectData{
    @Constraints.Required(message="Bitte füllen sie den Titel aus")
    public String title;
    @Constraints.Required
    public String description;
    @Constraints.Required
    public String endsAt;
    @Constraints.Required
    public String startsAt;

    public String validate() {
        if(!isDate(startsAt)){
            return "startsAt is not a Date";
        }else if(!isDate(endsAt)){
            return "endsAt is not a Date";
        }
        return null;
    }


    public static boolean isDate(String date){
        try{
            Converter.stringToSqlDate(date);
        }catch (AfricaException e){
            return false;
        }
        return true;
    }
}