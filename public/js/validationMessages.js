$(document).ready(function () {
    //Waren
    $("#amount").attr("data-parsley-error-message", "Bitte gib eine positive Zahl ein.");
    $("#donation").attr("data-parsley-error-message", "Bitte fülle das Feld mit den Spenden aus.");
    //Kontakt
    $("#city").attr("data-parsley-error-message", "Bitte gib den Zielort deiner Spenden an.");
    $("#country").attr("data-parsley-error-message", "Bitte gib das Zielland deiner Spenden an.");
    $("#contactInformation").attr("data-parsley-error-message", "Bitte gib an, wie du kontaktiert werden möchtest.");
    //newProject
    $("#title").attr("data-parsley-error-message", "Bitte gib deiner Spendenaktion einen Namen.");
    $("#imageURL").attr("data-parsley-error-message", "Bitte gib einen URL zu einem passenden Projektbild an.");
    $("#description").attr("data-parsley-error-message", "Bitte beschreibe dein Projekt in ein paar Worten.");
    $("#startsAt").attr("data-parsley-error-message", "Bitte gib an, ab wann dein Projekt startet.");
    $("#endsAt").attr("data-parsley-error-message", "Bitte gib an, wann dein Projekt endet.");
    //News
    $("#newsTitle").attr("data-parsley-error-message", "Bitte gib ein Newstitel an.");
    $("#newsDescription").attr("data-parsley-error-message", "Bitte gib ein Newsbeschreibung an.");
    $("#newsImageUrl").attr("data-parsley-error-message", "Bitte gib ein Bild URL an.");
    //Login
    $("#email").attr("data-parsley-error-message", "Bitte gib eine korrekte Emailadresse an.");
    $("#password").attr("data-parsley-error-message", "Bitte gib ein konformes Passwort an.");
    //Registration
    $("#firstname").attr("data-parsley-error-message", "Bitte gib deinen Vornamen an.");
    $("#lastname").attr("data-parsley-error-message", "Bitte gib deinen Nachnamen an.");
});
