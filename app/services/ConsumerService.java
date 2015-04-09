package services;

import com.avaje.ebean.Ebean;
import models.Consumer;
import play.db.ebean.Model;

public class ConsumerService {

    public void saveConsumer(Consumer consumer){
        consumer.save();
    }

    public Consumer getConsumerById(Long id){
        Model.Finder<Long, Consumer> finder = new Model.Finder<>(Long.class, Consumer.class);
        return finder.byId(id);
    }

    public Consumer getConsumerByEmail(String email){
        Model.Finder<String, Consumer> finder = new Model.Finder<>(String.class, Consumer.class);
        return finder.where().eq("email", email).findUnique();
    }

    public boolean authenticate(String email, String password) {

        return false;
    }
}
