package Integration;

import com.avaje.ebean.Ebean;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import play.api.db.DBApi;
import play.api.db.evolutions.Evolution;
import play.api.db.evolutions.Evolutions;
import play.libs.F;
import play.test.FakeApplication;
import play.test.Helpers;
import play.test.TestBrowser;
import scala.collection.Seq;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;


/**
 * Created by SKU on 02.04.2015.
 */
public class NewProjectTest2 {
    public static FakeApplication app;
    public static String createDdl = "";
    public static String dropDdl = "";

    @BeforeClass
    public static void startApp() throws IOException {
        app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
        Helpers.start(app);

        //System.out.println(app.getWrappedApplication().path().getAbsolutePath());

        // Reading the evolution file
        String evolutionContent = FileUtils.readFileToString(
                app.getWrappedApplication().getFile("../../conf/evolutions/default/1.sql"));

        // Splitting the String to get Create & Drop DDL
        String[] splittedEvolutionContent = evolutionContent.split("# --- !Ups");
        String[] upsDowns = splittedEvolutionContent[1].split("# --- !Downs");
        createDdl = upsDowns[0];
        dropDdl = upsDowns[1];

    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }

    @Before
    public void createCleanDb() {
        Ebean.execute(Ebean.createCallableSql(dropDdl));
        Ebean.execute(Ebean.createCallableSql(createDdl));
    }

    @Test
    public void projektDatenInputValidTest() {
        running(testServer(3333, app), new HtmlUnitDriver(BrowserVersion.CHROME), new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/projects/new");
                browser.$("#btnContinue1").click();
                assertThat(browser.pageSource().contains("error"));

                browser.$("[name='title']").text("Help Children get clothes");
                browser.$("#btnContinue1").click();
                assertThat(browser.pageSource().contains("error"));

                browser.$("[name='description']").text("Help Children get clothes");
                browser.$("#btnContinue1").click();
                assertThat(browser.pageSource().contains("error"));

                browser.$("[name='imageURL']").text("Some URL to image");
                browser.$("#btnContinue1").click();
                assertThat(browser.pageSource().contains("error"));

                browser.$("[name='startsAt']").text("2015-03-10");
                browser.$("#btnContinue1").click();
                assertThat(browser.pageSource().contains("error"));

                browser.$("[name='endsAt']").text("2015-04-29");
                browser.$("#btnContinue1").click();
                assertThat(!browser.pageSource().contains("error"));
            }
        });
    }


}
