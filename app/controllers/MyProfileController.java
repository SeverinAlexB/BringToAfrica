package controllers;

import models.Donation;
import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import services.ConsumerService;
import viewmodels.MyDonations.MyDonationsData;
import viewmodels.MyProfile;
import viewmodels.RegistrationData;

import java.util.List;

public class MyProfileController {
    @Security.Authenticated(AuthenticationController.class)
    public static Result myProfile() {
        User user = ApplicationController.getCurrentUser();
        MyProfile myProfile = new MyProfile();
        myProfile.id = user.getId().toString();
        myProfile.email = user.getEmail();
        myProfile.firstname = user.getFirstName();
        myProfile.lastname = user.getLastName();
        Form<MyProfile> myProfileForm = new Form<>(MyProfile.class);
        myProfileForm.fill(myProfile);
        return play.mvc.Controller.ok(views.html.ConsumerManagement.myProfile.render(myProfileForm));
    }

    public static Result saveMyProfile() {
        Form<MyProfile> myProfileForm = Form.form(MyProfile.class).bindFromRequest();
        if (myProfileForm.hasErrors()) {
            return play.mvc.Controller.badRequest(views.html.ConsumerManagement.myProfile.render(myProfileForm));
        } else {
            long id = Long.getLong(myProfileForm.get().id);
            User user = User.find.byId(id);
            if (ConsumerService.isValid(user.getEmail(), myProfileForm.get().oldPassword)) {
                String hash = BCrypt.hashpw(myProfileForm.get().password1, BCrypt.gensalt());
                user.setEmail(myProfileForm.get().email.toLowerCase());
                user.setFirstName(myProfileForm.get().firstname);
                user.setLastName(myProfileForm.get().lastname);
                user.setPasswordHashedSalted(hash);
                user.save();
                ConsumerService.logIn(user.getEmail());
                return play.mvc.Controller.redirect(routes.ApplicationController.index());
            } else {
                myProfileForm.reject("password","Altest Passwort ist nicht korrekt!");
                return play.mvc.Controller.badRequest(views.html.ConsumerManagement.myProfile.render(myProfileForm));
            }
        }
    }
}
