package uk.ac.cf.cs.beetle;

import org.junit.Test;
import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RegularBeetleTest {

    @Test
    public void shouldAddBodyPartWhenCanAddBodyPart() throws Exception {
        RegularBeetle beetle = new RegularBeetle();
        IBodyPart mockBodyPart = mock(IBodyPart.class);
        given(mockBodyPart.canAppendToBeetle(beetle)).willReturn(true);

        try {
            beetle.addBodyPart(mockBodyPart);
            assertThat(beetle.getBodyParts().size(), equalTo(1));
        } catch (InvalidBodyPartSequence invalidBodyPartSequence) {
            invalidBodyPartSequence.printStackTrace();
            fail("Should not throw exception");
        }
    }

    @Test
    public void shouldThrowInvalidBodyPartSequenceWhenCannotAddBodyPart() throws Exception {
        // given
        RegularBeetle beetle = new RegularBeetle();
        IBodyPart mockBodyPart = mock(IBodyPart.class);
        InvalidBodyPartSequence exceptionThrown = new InvalidBodyPartSequence("Can't add this");
        given(mockBodyPart.canAppendToBeetle(beetle)).willThrow(exceptionThrown);

        // when
        InvalidBodyPartSequence caughtException = null;
        try {
            beetle.addBodyPart(mockBodyPart);
        } catch (InvalidBodyPartSequence invalidBodyPartSequence) {
            caughtException = invalidBodyPartSequence;
        }

        assertThat(caughtException, notNullValue());
        assertThat(caughtException, is(exceptionThrown));
    }
}
