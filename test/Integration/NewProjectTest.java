package Integration;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import models.Project;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import play.db.ebean.Model;
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
    private boolean projektdatenCalled = false;;
    public void gotoProjektdaten(TestBrowser browser) {
        browser.goTo("http://localhost:3333/projects/new");
        projektdatenCalled = true;
    }
    private boolean warenCalled = false;
    public void gotoWaren(TestBrowser browser) {
        assert projektdatenCalled;
        browser.$("[name='title']").text("Help Children get clothes");
        browser.$("[name='description']").text("Help Children get clothes");
        browser.$("[name='imageURL']").text("Some URL to Image");
        browser.$("[name='startsAt']").text("2015-03-10");
        browser.$("[name='endsAt']").text("2015-04-29");
        //browser.$("#btnContinue1").click();
        warenCalled = true;
    }
    private boolean kontaktCalled = false;
    public void gotoKontakt(TestBrowser browser) {
        assert warenCalled;
        browser.$("[name='amountField']").text("10");
        browser.$("[name='donationField']").text("Schuhe");
        browser.$("#btnContinue2").click();
        kontaktCalled = true;
    }
    private boolean bestaetigungCalled = false;
    public void gotoBestaetigung(TestBrowser browser) {
        //assert kontaktCalled;
        browser.$("[name='country']").text("Nigeria");
        browser.$("[name='city']").text("New York");
        browser.$("[name='contact']").text("Claudia Hofstetter\nLehstrasse 5\n 8000 Zürich");
        browser.$("#btnContinue3").click();
        bestaetigungCalled = true;
    }

    @Test
    public void straightThroughTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                gotoProjektdaten(browser);
                assertThat(!browser.pageSource().contains("error"));
                gotoWaren(browser);
                assertThat(!browser.pageSource().contains("error"));
                //gotoKontakt(browser);
                //assertThat(!browser.pageSource().contains("error"));
                gotoBestaetigung(browser);
                assertThat(!browser.pageSource().contains("error"));
            }
        });
    }
    @Test
    public void projektDatenInputValidTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new F.Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                gotoProjektdaten(browser);
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
