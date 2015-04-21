import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.dbplatform.H2Platform;
import com.avaje.ebean.config.dbplatform.PostgresPlatform;
import com.avaje.ebeaninternal.api.SpiEbeanServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;
import models.User;
import play.*;
import play.libs.Yaml;

import java.util.*;

public class Global extends GlobalSettings {
    private boolean loadTestData = true;
    @Override
    public void onStart(Application app) {
        if(loadTestData && !isJUnitTest()) {
            cleanDatabase();
            fillDatabase("testFiles/placeholder-data.yml");
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
        String databaseType = Play.application().configuration().getString("databaseType");
        if(databaseType.equals("postgres")) {
            ddl.setup((SpiEbeanServer) server, new PostgresPlatform(), config);
        } else {
            ddl.setup((SpiEbeanServer) server, new H2Platform(), config);
        }
        ddl.runScript(false, ddl.generateDropDdl());
        ddl.runScript(false, ddl.generateCreateDdl());
        assert User.find.all().size() == 0;
    }

    private  void fillDatabase(String yamlFile) {
        Object yam = Yaml.load(yamlFile);
        if(yam instanceof ArrayList) {
            Ebean.save((List) yam);
        } else {
            Map<String,List<Object>> yamMap = (Map) yam;
            for(String s: yamMap.keySet()){
                Ebean.save(yamMap.get(s));
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
