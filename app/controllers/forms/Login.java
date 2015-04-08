package controllers.forms;

import play.data.validation.Constraints;

public class Login {
    @Constraints.Required(message="Bitte gib eine Emailadresse an.")
    public String email;
    @Constraints.Required(message="Bitte gib eine Passwort an.")
    public String password;
}
