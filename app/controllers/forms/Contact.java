package controllers.forms;


import play.data.validation.Constraints;

public class Contact{
    @Constraints.Required(message="Bitte fülle deine Kontaktmöglichkeiten aus")
    public String contact;
    @Constraints.Required(message="Gib dein Land an")
    public String country;
    @Constraints.Required(message="Gib die Stadt an")
    public String city;
}