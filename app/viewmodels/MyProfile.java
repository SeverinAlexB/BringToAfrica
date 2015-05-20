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
        User user = User.find.byId(Long.parseLong(this.id));
        if(!isUserValid(user))return "User oder aktuelles Passwort ungültig.";
        if(this.changePw())return isNewPassswordVaild();
        return null;
    }


    public boolean changePw(){
        if(changePassword == null || changePassword == "true")return true;
        return false;
    }

    private boolean isUserValid(User user){
        if(user == null)return false;
        return isOldPasswordValid(user);
    }

    private boolean isOldPasswordValid(User user){
        return ConsumerService.isValid(user.getEmail(), this.oldPassword);
    }

    private String isNewPassswordVaild(){
        if( ConsumerService.validatePasswords(password1,password2))return null;
        return "neues Passwort ungültig";
    }
}
