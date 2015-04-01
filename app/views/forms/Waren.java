package views.forms;

import play.data.validation.Constraints;

public class Waren{
    @Constraints.Required(message="Bitte gib eine Menge an")
    @Constraints.Pattern(value="[1-9]\\d*", message = "Es muss eine positive Zahl eingegeben werden")
    public String amount;
    @Constraints.Required(message="Beschreibe was du spenden willst")
    public String donation;
}