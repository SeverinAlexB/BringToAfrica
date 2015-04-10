package controllers.forms;

import play.data.validation.Constraints;

/**
 * Created by Severin on 10.04.2015.
 */
public class Registration {
    @Constraints.Required(message="Bitte gib deinen Vornamen hier an.")
    public String firstname;
    @Constraints.Required(message="Bitte gib eine Nachnamen hier an.")
    public String lastname;
    @Constraints.Required(message="Bitte gib eine Emailadresse an.")
    @Constraints.Email(message = "Bitte gib eine gueltige Emailadresse an")
    public String email;
    @Constraints.Required(message="Bitte gib eine Passwort zum ersten Mal an.")
    @Constraints.MinLength(6)
    public String password1;
    @Constraints.Required(message="Bitte gib eine Passwort zum zweiten Mal an.")
    public String password2;
}