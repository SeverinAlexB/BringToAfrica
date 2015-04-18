package viewmodels;

import models.User;
import play.data.validation.Constraints;
import services.ConsumerService;

public class MyProfile {
    @Constraints.Required(message = "Profil hat keine id")
    public String id;
    @Constraints.Required(message = "Bitte gib deinen Vornamen hier an.")
    public String firstname;
    @Constraints.Required(message = "Bitte gib eine Nachnamen hier an.")
    public String lastname;
    @Constraints.Required(message = "Bitte gib eine Emailadresse an.")
    @Constraints.Email(message = "Bitte gib eine gültige Emailadresse an")
    public String email;

    @Constraints.Required(message = "Bitte gib dein aktuelles Passwort an.")
    public String oldPassword;

    public String password1;
    public String password2;
    public String changePassword;

    public String validate() {
        long id = Long.parseLong(this.id);
        User user = User.find.byId(id);
        if (!ConsumerService.isValid(user.getEmail(), this.oldPassword)) {
            return "aktuelles Passwort ungültig";
        }
        return null;
    }
}
