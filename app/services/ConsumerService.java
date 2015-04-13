package services;

import models.Consumer;
import org.mindrot.jbcrypt.BCrypt;

public class ConsumerService {

    public static Consumer getConsumerByEmail(String email){
        if(email == null) {
            return null;
        }
        return Consumer.find.where().like("email", email.toLowerCase()).findUnique();
    }

    public static boolean isValid(String email, String password) {
        Consumer consumer = getConsumerByEmail(email);
        if(consumer == null) {
            return false;
        } else if(!BCrypt.checkpw(password,consumer.getPasswordHashedSalted())) {;
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
