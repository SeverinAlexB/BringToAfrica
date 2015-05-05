package controllers;

import models.User;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import services.ConsumerService;
import viewmodels.MyProfile;

public class MyProfileController {
    @Security.Authenticated(AuthenticationController.class)
    public static Result myProfile() {
        User user = ApplicationController.getCurrentUser();
        MyProfile myProfile = new MyProfile();
        myProfile.id = user.getId();
        myProfile.email = user.getEmail();
        myProfile.firstname = user.getFirstName();
        myProfile.lastname = user.getLastName();
        Form<MyProfile> myProfileForm = Form.form(MyProfile.class).fill(myProfile);
        return play.mvc.Controller.ok(
            views.html.user.myProfile.render(myProfileForm)
        );
    }

    @Security.Authenticated(AuthenticationController.class)
    public static Result saveMyProfile() {
        Form<MyProfile> myProfileForm = Form.form(MyProfile.class).bindFromRequest();
        if (myProfileForm.hasErrors()) {
            return play.mvc.Controller.badRequest(
                views.html.user.myProfile.render(myProfileForm)
            );
        } else {
            User user = User.find.byId(myProfileForm.get().id);
            if (myProfileForm.get().changePw())editPassword(myProfileForm, user);
            saveProfile(user, myProfileForm);
            ConsumerService.logIn(user.getEmail());
            return play.mvc.Controller.redirect(routes.ApplicationController.index());
        }
    }

    private static void saveProfile(User user, Form<MyProfile> myProfileForm){
        user.setEmail(myProfileForm.get().email.toLowerCase());
        user.setFirstName(myProfileForm.get().firstname);
        user.setLastName(myProfileForm.get().lastname);
        user.save();
    }


    private static boolean editPassword(Form<MyProfile> myProfileForm, User user) {
        String oldPassword = myProfileForm.get().oldPassword;
        String newPassword1 = myProfileForm.get().password1;
        String newPassword2 = myProfileForm.get().password2;
        return ConsumerService.changePassword(user, oldPassword, newPassword1);
    }
}
