package drooser;

import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RuleTest {

    private static KieSession kSession;
    private User bob = new User("groupama");
    private User jane = new User("allianz");

    @BeforeAll
    public static void setup() {
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kContainer = kieServices.getKieClasspathContainer();
            kSession = kContainer.newKieSession("ksession-rule");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDown() {
        if (kSession != null) {
            kSession.dispose();
        }
    }

    @Test
    @DisplayName("All rules fired")
    public void testRulesFired() throws Exception {
        bob.setTestResult(85);
        bob.setLicenseAge(5);
        kSession.insert(bob);

        AgendaEventListener eventListener = mock(AgendaEventListener.class);
        kSession.addEventListener(eventListener);

        int rulesFired = kSession.fireAllRules();

        assertEquals( 3, rulesFired );
    }

    @Test
    @DisplayName("Test Rules Sequence")
    public void testRulesSequence() throws Exception {
        bob.setTestResult(85);
        bob.setLicenseAge(5);
        kSession.insert(bob);

        // adding an event listener for capturing rule sequence
        ArgumentCaptor<AfterMatchFiredEvent> captor = ArgumentCaptor.forClass(AfterMatchFiredEvent.class);
        AgendaEventListener eventListener = mock(AgendaEventListener.class);
        kSession.addEventListener(eventListener);
        kSession.fireAllRules();

        verify(eventListener, times(3)).afterMatchFired(captor.capture());
        List<AfterMatchFiredEvent> events = captor.getAllValues();

        List<String> expected = new ArrayList<String>(Arrays.asList("Approve user",
                                                                    "Set Discount for employees",
                                                                    "Set test result for rejection"));
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < events.size(); i++) {
            result.add(events.get(i).getMatch().getRule().getName());
        }

        assertEquals(expected, result);

    }

    @Test
    @DisplayName("Employee discount fired")
    public void testEmployeeDiscountRuleFire() throws Exception {
        bob.setTestResult(85);
        bob.setLicenseAge(5);
        kSession.insert(bob);

        int rulesFired = kSession.fireAllRules(new RuleNameEqualsAgendaFilter("Set Discount for employees"));

        assertEquals(1, rulesFired);
    }

    @Test
    @DisplayName("Non-Employee discount fired")
    public void testNonEmployeeDiscountRuleFire() throws Exception {
        jane.setTestResult(85);
        jane.setLicenseAge(5);
        kSession.insert(jane);

        int rulesFired = kSession.fireAllRules(new RuleNameEqualsAgendaFilter("Set Discount for non-employees"));

        assertEquals(1, rulesFired);
    }

    @Test
    @DisplayName("10% discount added")
    public void testEmployeeDiscountRuleAmount() throws Exception {
        bob.setTestResult(85);
        bob.setLicenseAge(5);
        kSession.insert(bob);

        kSession.fireAllRules(new RuleNameEqualsAgendaFilter("Set Discount for employees"));
        int discount = bob.getDiscount();

        assertEquals(10, discount);
    }

    @Test
    @DisplayName("5% discount added")
    public void testNonEmployeeDiscountRuleAmount() throws Exception {
        jane.setTestResult(85);
        jane.setLicenseAge(5);
        kSession.insert(jane);

        kSession.fireAllRules(new RuleNameEqualsAgendaFilter("Set Discount for non-employees"));
        int discount = jane.getDiscount();

        assertEquals(5, discount);
    }

    @Test
    @DisplayName("Approve user fired")
    public void testApproveUserRuleFire() throws Exception {
        bob.setTestResult(85);
        bob.setLicenseAge(5);
        kSession.insert(bob);

        int rulesFired = kSession.fireAllRules(new RuleNameEqualsAgendaFilter("Approve user"));

        assertEquals(1, rulesFired);
    }

    @Test
    @DisplayName("User Approved")
    public void testUserApproved() throws Exception {
        bob.setTestResult(85);
        bob.setLicenseAge(5);
        kSession.insert(bob);

        int rulesFired = kSession.fireAllRules(new RuleNameEqualsAgendaFilter("Approve user"));

        assertEquals(true, bob.getApproved());
    }

    @Test
    @DisplayName("User Declined")
    public void testUserDeclined() throws Exception {
        bob.setTestResult(55);
        bob.setLicenseAge(5);
        kSession.insert(bob);

        int rulesFired = kSession.fireAllRules(new RuleNameEqualsAgendaFilter("Decline user"));

        assertEquals(false, bob.getApproved());
    }


}