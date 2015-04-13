package services;

import models.User;
import org.mindrot.jbcrypt.BCrypt;

public class ConsumerService {

    public static User getConsumerByEmail(String email){
        if(email == null) {
            return null;
        }
        return User.find.where().like("email", email.toLowerCase()).findUnique();
    }

    public static boolean isValid(String email, String password) {
        User user = getConsumerByEmail(email);
        if(user == null) {
            return false;
        } else if(!BCrypt.checkpw(password, user.getPasswordHashedSalted())) {;
            return false;
        }else{
            return true;
        }
    }
    public static void logIn(String email){
        play.mvc.Controller.session().clear();
        play.mvc.Controller.session("email", email);
    }


}
