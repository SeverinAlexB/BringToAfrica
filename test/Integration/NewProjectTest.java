package Integration;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Test;
import play.libs.F;
import play.test.TestBrowser;
import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

import static play.test.Helpers.running;

public class NewProjectTest {

    @Test
    public void test2() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/projects/new");

              //  assertThat(browser.pageSource()).contains("Add Project");
            }
        });
    }

}
