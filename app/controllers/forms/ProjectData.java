package controllers.forms;

import controllers.AfricaException;
import play.data.validation.Constraints;

import java.util.List;

public class ProjectData{
    @Constraints.Required(message="Bitte füllen sie den Titel aus")
    public String title;
    @Constraints.Required
    public String description;
    @Constraints.Required
    public String endsAt;
    @Constraints.Required
    public String startsAt;

    @Constraints.Required(message="Bitte fülle deine Kontaktmöglichkeiten aus")
    public String contact;
    @Constraints.Required(message="Gib dein Land an")
    public String country;
    @Constraints.Required(message="Gib die Stadt an")
    public String city;

    //@Constraints.Required(message="Bitte gib eine Menge an")
    //@Constraints.Pattern(value="[1-9]\\d*", message = "Es muss eine positive Zahl eingegeben werden")
    public List<String> amounts;
    //@Constraints.Required(message="Beschreibe was du spenden willst")
    public List<String> donations;

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