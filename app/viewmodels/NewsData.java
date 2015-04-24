package viewmodels;

import play.data.validation.Constraints;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NewsData {
    @Constraints.Required
    public String projectId;

    @Constraints.Required(message = "Bitte gib einen Titel ein")
    public String title;
    @Constraints.Required(message = "Bitte gib eine kurze Beschreibung an")
    public String description;

    public String imageUrl;

    public String validate() {
        if(imageUrl != null){
            try {
                URL url = new URL(imageUrl);
                URLConnection conn = url.openConnection();
                conn.connect();
            } catch (MalformedURLException e) {
                return "the URL is not in a valid form";
            } catch (IOException e) {
                return "the connection couldn't be established";
            }
        }
        return null;
    }
}
