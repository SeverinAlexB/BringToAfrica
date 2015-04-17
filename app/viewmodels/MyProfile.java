package viewmodels;

import play.data.validation.Constraints;

public class MyProfile {
    @Constraints.Required
    public String id;
    @Constraints.Required(message = "Bitte gib deinen Vornamen hier an.")
    public String firstname;
    @Constraints.Required(message = "Bitte gib eine Nachnamen hier an.")
    public String lastname;
    @Constraints.Required(message = "Bitte gib eine Emailadresse an.")
    @Constraints.Email(message = "Bitte gib eine gültige Emailadresse an")
    public String email;

    @Constraints.Required(message = "Bitte gib dein altes Passwort an.")
    public String oldPassword;

    @Constraints.Required(message = "Bitte gib eine neues Passwort zum ersten Mal an.")
    public String password1;
    @Constraints.Required(message = "Bitte gib eine neues Passwort zum zweiten Mal an.")
    public String password2;

    public String validate() {
        if (!password1.equals(password2)) {
            password1 = "";
            password2 = "";
            return "Die beiden Passwörter stimmen nicht überein.";
        }
        return null;
    }
}
