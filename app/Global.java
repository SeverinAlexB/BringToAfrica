import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import models.User;
import play.*;
import play.libs.Yaml;

import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        if (!isJUnitTest()) {
            cleanDatabase();
            fillDatabase("testFiles/data1.yml");
            Logger.info("test data loaded");
        }
    }

    @Override
    public void onStop(Application app) {
        //Logger.info("Application shutdown...");
    }

    private  void cleanDatabase(){
        EbeanServer server = Ebean.getServer("default");
        ServerConfig config = new ServerConfig();
        DdlGenerator ddl = new DdlGenerator();
        ddl.setup((SpiEbeanServer) server, new H2Platform(), config);
        ddl.runScript(false, ddl.generateDropDdl());
        ddl.runScript(false, ddl.generateCreateDdl());
        assert User.find.all().size() == 0;
    }

    private  void fillDatabase(String yamlFile) {
        Object yaml = Yaml.load(yamlFile);
        if (yaml instanceof ArrayList) {
            Ebean.save((List) yaml);
        } else {
            Map<String,List<Object>> yamlMap = (Map<String, List<Object>>) yaml;
            for (String s: yamlMap.keySet()) {
                Ebean.save(yamlMap.get(s));
            }
        }
    }

    private  boolean isJUnitTest() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        List<StackTraceElement> list = Arrays.asList(stackTrace);
        for (StackTraceElement element : list) {
            if (element.getClassName().startsWith("org.junit.")) {
                return true;
            }
        }
        return false;
    }
}
