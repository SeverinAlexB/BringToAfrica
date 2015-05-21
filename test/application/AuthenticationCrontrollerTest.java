package application;

import integration.DatabaseTest;
import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import static play.test.Helpers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AuthenticationCrontrollerTest {
    @Test
    public void loginWithBadSessionUser() {
        DatabaseTest.runInCleanApp((browser -> {
            String email = "michael.blocher@msn.com";

            Result result = callAction(
                    controllers.routes.ref.ProjectController.addProjectData(),
                    fakeRequest().withSession("email", email));
            assertEquals(303, status(result));
            Http.Flash flash = flash(result);
            //Http.Session session = session(result);
            String value = flash.get("bad");
            assertTrue(value.equals("Session wurde gecleart!"));
        }));
    }
}
