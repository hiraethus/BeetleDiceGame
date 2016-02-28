package uk.ac.cf.cs.beetle;

import org.junit.Test;
import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class HeadTest {
    private final Head head = new Head();

    @Test
    public void shouldAddHeadWhenBeetleHasBody() throws InvalidBodyPartSequence {
        // given
        Beetle mockBeetle = mock(Beetle.class);

        ArrayList<IBodyPart> justABody = new ArrayList<>(0);
        justABody.add(new Body());
        given(mockBeetle.getBodyParts()).willReturn(justABody);


        // when
        boolean result = head.canAppendToBeetle(mockBeetle);

        // then
        assertThat(result, is(true));
    }

    @Test
    public void shouldNotAddHeadWhenBeetleDoesntHaveABody() throws InvalidBodyPartSequence {
        // given
        Beetle mockBeetle = mock(Beetle.class);

        ArrayList<IBodyPart> emptyBody = new ArrayList<>(0);
        given(mockBeetle.getBodyParts()).willReturn(emptyBody);

        // when
        String errorMsg = null;
        try {
            boolean result = head.canAppendToBeetle(mockBeetle);
        } catch (InvalidBodyPartSequence e) {
            errorMsg = e.getMessage();
        }

        // then
        assertThat(errorMsg, notNullValue());
        assertThat(errorMsg, equalTo("The beetle needs a Body to have a Head"));
    }

    @Test
    public void shouldNotAddHeadWhenBeetleAlreadyHasABody() throws InvalidBodyPartSequence {
        // given
        Beetle mockBeetle = mock(Beetle.class);

        ArrayList<IBodyPart> headAndBodyOnly = new ArrayList<>(0);
        headAndBodyOnly.add(new Head());
        headAndBodyOnly.add(new Body());
        given(mockBeetle.getBodyParts()).willReturn(headAndBodyOnly);

        // when
        String errorMsg = null;
        try {
            boolean result = head.canAppendToBeetle(mockBeetle);
        } catch (InvalidBodyPartSequence e) {
            errorMsg = e.getMessage();
        }

        // then
        assertThat(errorMsg, notNullValue());
        assertThat(errorMsg, equalTo("The beetle already has a Head"));
    }
}
