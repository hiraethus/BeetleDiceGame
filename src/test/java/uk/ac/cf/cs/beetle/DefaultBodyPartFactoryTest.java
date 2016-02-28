package uk.ac.cf.cs.beetle;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

import java.util.Collection;

public class DefaultBodyPartFactoryTest {

    @Test
    public void shouldAddMappings() {
        DefaultBodyPartFactory factory = new DefaultBodyPartFactory();
        DieRollToBodyPartMapping mapping1 = new DieRollToBodyPartMapping(1, Body.class);
        try {
            factory.addMapping(mapping1);
        } catch (InvalidDieRollToBodyPartMapping e) {
            fail("Threw Invalid DieRollToBodyPartMapping exception for just one mapping");
        }

        Collection<DieRollToBodyPartMapping> result = factory.getMappings();

        assertThat(result.size(), equalTo(1));
        assertThat(result.stream().findFirst().get(), is(mapping1));
    }

    @Test
    public void shouldNotAddMappingWhenMappingFactoryContainsSameBodyPart() {
        DefaultBodyPartFactory factory = new DefaultBodyPartFactory();
        DieRollToBodyPartMapping mapping1 = new DieRollToBodyPartMapping(1, Body.class);
        DieRollToBodyPartMapping mapping2 = new DieRollToBodyPartMapping(2, Body.class);

        String errorMsg = null;
        try {
            factory.addMapping(mapping1);
            factory.addMapping(mapping2);
        } catch (InvalidDieRollToBodyPartMapping e) {
            errorMsg = e.getMessage();
        }

        assertThat(errorMsg, notNullValue());
        assertThat(errorMsg, is("The Body class has already been added to this factory"));
    }

    @Test
    public void shouldNotAddMappingWhenMappingFactoryContainsSameDieRollValue() {
        DefaultBodyPartFactory factory = new DefaultBodyPartFactory();
        DieRollToBodyPartMapping mapping1 = new DieRollToBodyPartMapping(1, Body.class);
        DieRollToBodyPartMapping mapping2 = new DieRollToBodyPartMapping(1, Head.class);

        String errorMsg = null;
        try {
            factory.addMapping(mapping1);
            factory.addMapping(mapping2);
        } catch (InvalidDieRollToBodyPartMapping e) {
            errorMsg = e.getMessage();
        }

        assertThat(errorMsg, notNullValue());
        assertThat(errorMsg, is("The die number 1 has already been added to this factory"));
    }

    @Test
    public void shouldAddMappingWhenMappingFactoryDoesntContainSameBodyPartOrSameDieNumber() {
        DefaultBodyPartFactory factory = new DefaultBodyPartFactory();
        DieRollToBodyPartMapping mapping1 = new DieRollToBodyPartMapping(1, Body.class);
        DieRollToBodyPartMapping mapping2 = new DieRollToBodyPartMapping(2, Head.class);

        try {
            factory.addMapping(mapping1);
            factory.addMapping(mapping2);
        } catch (InvalidDieRollToBodyPartMapping e) {
            fail("Threw Invalid DieRollToBodyPartMapping exception for just two unique mappings");
        }

        Collection<DieRollToBodyPartMapping> result = factory.getMappings();

        assertThat(result.size(), equalTo(2));
        assertThat(result.toArray()[0], is(mapping1));
        assertThat(result.toArray()[1], is(mapping2));
    }

    @Test
    public void shouldThrowExceptionWhenFactoryDoesntHaveTheDieValueToCreateABodyPart() {
        DefaultBodyPartFactory factory = new DefaultBodyPartFactory();
        DieRollToBodyPartMapping mapping1 = new DieRollToBodyPartMapping(1, Body.class);
        DieRollToBodyPartMapping mapping2 = new DieRollToBodyPartMapping(2, Head.class);
        try {
            factory.addMapping(mapping1);
            factory.addMapping(mapping2);
        } catch (InvalidDieRollToBodyPartMapping e) {
            fail("There should be no issue adding both of these mappings - problem with test");
        }

        String errorMsg = null;
        try {
            factory.createBodyPart(3);
        } catch (InvalidDieValue e) {
            errorMsg = e.getMessage();
        }

        assertThat(errorMsg, notNullValue());
        assertThat(errorMsg, equalTo("3 is not an available die value for this factory"));
    }

    @Test
    public void shouldReturnNewBodyPartWhenDieValueIsCorrect() {
        DefaultBodyPartFactory factory = new DefaultBodyPartFactory();
        DieRollToBodyPartMapping mapping1 = new DieRollToBodyPartMapping(1, Body.class);
        try {
            factory.addMapping(mapping1);
        } catch (InvalidDieRollToBodyPartMapping e) {
            fail("There should be no issue adding both of these mappings - problem with test");
        }

        IBodyPart bodyPart = null;
        try {
            bodyPart = factory.createBodyPart(1);
        } catch (InvalidDieValue e) {
            fail("The factory should have successfully instantiated a Body IBodyPart");
        }

        assertThat(bodyPart, notNullValue());
        assertThat(bodyPart.getClass(), equalTo(Body.class));
    }
}
