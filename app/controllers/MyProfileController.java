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
        myProfile.id = user.getId().toString();
        myProfile.email = user.getEmail();
        myProfile.firstname = user.getFirstName();
        myProfile.lastname = user.getLastName();
        Form<MyProfile> myProfileForm = Form.form(MyProfile.class).fill(myProfile);
        return play.mvc.Controller.ok(
            views.html.ConsumerManagement.myProfile.render(myProfileForm)
        );
    }

    @Security.Authenticated(AuthenticationController.class)
    public static Result saveMyProfile() {
        Form<MyProfile> myProfileForm = Form.form(MyProfile.class).bindFromRequest();
        if (myProfileForm.hasErrors()) {
            return play.mvc.Controller.badRequest(
                views.html.ConsumerManagement.myProfile.render(myProfileForm)
            );
        } else {
            long id = Long.parseLong(myProfileForm.get().id);
            User user = User.find.byId(id);
            if (myProfileForm.get().changePassword != null &&
                myProfileForm.get().changePassword.equals("true")) {
                if (!editPassword(myProfileForm, user)) {
                    myProfileForm.reject("password", "Konnte Passwort nicht ändern!");
                    return play.mvc.Controller.badRequest(
                        views.html.ConsumerManagement.myProfile.render(myProfileForm)
                    );
                }
            }
            user.setEmail(myProfileForm.get().email.toLowerCase());
            user.setFirstName(myProfileForm.get().firstname);
            user.setLastName(myProfileForm.get().lastname);
            user.save();
            ConsumerService.logIn(user.getEmail());
            return play.mvc.Controller.redirect(routes.ApplicationController.index());
        }
    }

    private static boolean editPassword(Form<MyProfile> myProfileForm, User user) {
        String oldPassword = myProfileForm.get().oldPassword;
        String newPassword1 = myProfileForm.get().password1;
        String newPassword2 = myProfileForm.get().password2;

        return ConsumerService.validatePasswords(newPassword1, newPassword2) &&
                ConsumerService.changePassword(user, oldPassword, newPassword1);
    }
}
