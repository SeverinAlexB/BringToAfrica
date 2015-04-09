package services;

import com.avaje.ebean.Ebean;
import models.Consumer;

public class ConsumerService {

    public void saveConsumer(Consumer consumer){
        consumer.save();
    }

    public Consumer getConsumerById(Long id){
        return Ebean.find(Consumer.class, id);
    }

    public Consumer getConsumerByEmail(String email){
        return Ebean.find(Consumer.class).where().eq("email", email).findUnique();
    }

    public boolean authenticate(String email, String password) {
        Consumer consumer = Ebean.find(Consumer.class).where().ieq("email", email).eq("password", password).findUnique();
        System.out.println("consumer: " + consumer.getEmail());
        if(consumer == null){
            return false;
        }else{
            return true;
        }
    }
}
