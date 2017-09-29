package drooser;


import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Rule {

    private Logger logger = LoggerFactory.getLogger(Rule.class);

    public static void executeRule(User user) {

        try {
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kContainer = kieServices.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rule");
            kSession.insert(user);
            kSession.fireAllRules();
            kSession.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
