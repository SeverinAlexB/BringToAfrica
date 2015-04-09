package services;

import models.Consumer;


public class ConsumerService {

    public void saveConsumer(Consumer consumer){
        consumer.save();
    }

    public Consumer getConsumerById(Long id){
        return Consumer.find.byId(id);
    }

    public Consumer getConsumerByEmail(String email){
        return Consumer.find.where().eq("email", email).findUnique();
    }

    public boolean authenticate(String email, String password) {
        Consumer consumer = Consumer.find.where().eq("email",email).eq("password",password).findUnique();
        if(consumer == null){
            System.out.println("consumer not found");
            return false;
        }else{
            System.out.println("consumer: " + consumer.getEmail());
            return true;
        }
    }
}
