package info.solidsoft.java8;

import info.solidsoft.java8.defmethods.Engine;
import info.solidsoft.java8.defmethods.Job;
import info.solidsoft.java8.defmethods.Lifecycle;
import info.solidsoft.java8.defmethods.RuleEngine;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Conflicting default methods
 * First let {@link RuleEngine} implement all three interfaces.
 * Then decide which of the conflicting implementations should be chosen.
 */
public class J02b_ConflictingDefaultMethodsTest {

    private final RuleEngine ruleEngine = new RuleEngine();

    @Test
    public void shouldExtendFewInterfaces() {
        assertThat(ruleEngine).isInstanceOf(Job.class);
        assertThat(ruleEngine).isInstanceOf(Engine.class);
        assertThat(ruleEngine).isInstanceOf(Lifecycle.class);
    }

    @Test
    public void shouldReturnValueFromJob() {
        assertThat(ruleEngine.start()).isEqualTo("Job");
    }

}

