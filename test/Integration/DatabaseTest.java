package Integration;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebean.config.dbplatform.MySqlPlatform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import models.Consumer;
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
import play.libs.Yaml;
import play.test.FakeApplication;
import play.test.Helpers;
import play.test.TestBrowser;
import scala.collection.Seq;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;


/**
 * Created by SKU on 02.04.2015.
 */
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
        postgres.put("db.default.driver","org.h2.Driver");
        postgres.put("db.default.url","jdbc:h2:~/test");
        postgres.put("db.default.user", "sa");
        postgres.put("db.default.password", "");

        postgres.put("ebean.default","models.*");
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
        assert Consumer.find.all().size() == 0;
    }


    private static FakeApplication getApp() {
        HashMap<String,String> database = getH2TestDB();
        cleanDatabase(database);
        return fakeApplication(database);
    }

    public static void runInApp(F.Callback<TestBrowser> run) {
        running(testServer(3333, getApp()), new HtmlUnitDriver(BrowserVersion.CHROME), run);
    }

    @Test
    public void testFakeDataBase() {
        runInApp((TestBrowser t) -> {
            assertThat(Consumer.find.findUnique() == null);

            for (Consumer co : Consumer.find.all()) {
                co.delete();
            }
            Consumer c = new Consumer();
            c.setEmail("sevi_buehler@hotmail.com");
            c.setFirstName("Severin2");
            c.setLastName("blaaa");
            c.setPasswordHashedSalted("afesadsfasdf");
            c.save();

            assertThat(Consumer.find.findUnique() != null);

        });
    }
    @Test
    public void testFakeDataBaseFull() {
        runInApp((TestBrowser t) -> {
            Ebean.save((List) Yaml.load("test-data.yml"));
            assertThat(Consumer.find.findUnique() != null);
            Consumer testConsumer = Consumer.find.where().like("email", "bob@gmail.com").findUnique();
            assertThat(testConsumer.getEmail() == "bob@gmail.com");
        });
    }
}
