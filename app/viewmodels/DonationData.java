package viewmodels;

import play.data.validation.Constraints;

public class DonationData {
    @Constraints.Required(message = "Bitte gib einen Titel ein")
    public String title;
}
