package viewmodels;

import controllers.AfricaException;
import play.data.validation.Constraints;

import java.util.List;

public class ProjectData {
    @Constraints.Required(message="Bitte gib einen Titel ein")
    public String title;
    @Constraints.Required(message="Bitte gib eine kurze Beschreibung an")
    public String description;
    @Constraints.Required(message="Bitte füge ein Projektbild hinzu")
    public String imageURL;
    @Constraints.Required(message="Bitte gib an wann dein Projekt starten soll")
    public String startsAt;
    @Constraints.Required(message="Bitte gib an wann dein Projekt enden soll")
    public String endsAt;


    @Constraints.Required(message="Bitte fülle deine Kontaktmöglichkeiten aus")
    public String contactInformation;
    @Constraints.Required(message="Bitte gib das Spendenland an")
    public String country;
    @Constraints.Required(message="Bitte gib die Spendenstadt an")
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
            DateConverter.stringToSqlDate(date);
        }catch (AfricaException e){
            return false;
        }
        return true;
    }
}