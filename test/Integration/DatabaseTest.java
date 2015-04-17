package Integration;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import models.User;
import org.junit.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import play.libs.F;
import play.libs.Yaml;
import play.test.FakeApplication;
import play.test.Helpers;
import play.test.TestBrowser;

import java.util.HashMap;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class DatabaseTest {
    private static HashMap<String,String> getPgTestDB() {
        final HashMap<String,String> postgres = new HashMap<String, String>();
        postgres.put("db.default.driver","org.postgresql.Driver");
        postgres.put("db.default.url","jdbc:postgresql://152.96.56.71:40000/bringtoafricatest");
        postgres.put("db.default.user", "postgres");
        postgres.put("db.default.password", "postgres");

        postgres.put("ebean.default","models.*");
        return postgres;
    }
    private static HashMap<String,String> getH2TestDB() {
        final HashMap<String,String> postgres = new HashMap<String, String>();
        postgres.put("db.default.driver", "org.h2.Driver");
        postgres.put("db.default.url","jdbc:h2:~/test");
        postgres.put("db.default.user", "sa");
        postgres.put("db.default.password", "");

        postgres.put("ebean.default", "models.*");
        return postgres;
    }
    private static void cleanDatabase(HashMap<String,String> database){
        FakeApplication app = fakeApplication(database);
        Helpers.start(app);
        EbeanServer server = Ebean.getServer("default");
        ServerConfig config = new ServerConfig();
        DdlGenerator ddl = new DdlGenerator();
        ddl.setup((SpiEbeanServer) server, new H2Platform(), config);
        ddl.runScript(false, ddl.generateDropDdl());
        ddl.runScript(false, ddl.generateCreateDdl());
        assert User.find.all().size() == 0;
    }
    private static void fillDatabase(HashMap<String,String> database) {
        FakeApplication app = fakeApplication(database);
        Helpers.start(app);
        Ebean.save((List) Yaml.load("test-data.yml"));
    }


    private static FakeApplication getApp(Boolean filledWithTestData) {
        HashMap<String,String> database = getH2TestDB();
        cleanDatabase(database);
        if(filledWithTestData) fillDatabase(database);
        return fakeApplication(database);
    }

    public static void runInCleanApp(F.Callback<TestBrowser> run) {
        running(testServer(3333, getApp(false)), new HtmlUnitDriver(BrowserVersion.CHROME), run);
    }
    public static void runInFilledApp(F.Callback<TestBrowser> run) {
        running(testServer(3333, getApp(true)), new HtmlUnitDriver(BrowserVersion.CHROME), run);
    }

    @Test
    public void testFakeDataBase() {
        runInCleanApp((TestBrowser t) -> {
            assertThat(User.find.findUnique() == null);

            for (User co : User.find.all()) {
                co.delete();
            }
            User c = new User();
            c.setEmail("sevi_buehler@hotmail.com");
            c.setFirstName("Severin2");
            c.setLastName("blaaa");
            c.setPasswordHashedSalted("afesadsfasdf");
            c.save();

            assertThat(User.find.findUnique() != null);

        });
    }
    @Test
    public void testFakeDataBaseFull() {
        runInFilledApp((TestBrowser t) -> {
            assertThat(User.find.findUnique() != null);
            User testUser = User.find.where().like("email", "bob@gmail.com").findUnique();
            assertThat(testUser.getEmail().equals("bob@gmail.com"));

        });
    }
}
