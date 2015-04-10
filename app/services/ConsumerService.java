package services;

import models.Consumer;
import org.mindrot.jbcrypt.BCrypt;

public class ConsumerService {

    public static Consumer getConsumerById(Long id){
        return Consumer.find.byId(id);
    }

    public static Consumer getConsumerByEmail(String email){
        return Consumer.find.where().like("email", email).findUnique();
    }

    public static boolean isValid(String email, String password) {
        Consumer consumer = getConsumerByEmail(email);
        if(consumer == null) {
            System.out.println("consumer not found");
            return false;
        } else if(!BCrypt.checkpw(password,consumer.getPasswordHashedSalted())) {
            System.out.println("password wrong");
            return false;
        }else{
            System.out.println("all right, " + consumer.getEmail());
            return true;
        }
    }
    public static void logIn(String email){
        play.mvc.Controller.session().clear();
        play.mvc.Controller.session("email", email);
    }

}
