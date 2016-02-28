package uk.ac.cf.cs.beetle;

import org.junit.Test;
import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.*;
import java.util.ArrayList;

public class BodyTest {
	private Body body = new Body();

	@Test
	public void shouldAddBodyWhenBeetleDoesntHaveBody() throws InvalidBodyPartSequence {
		// given
		Beetle mockBeetle = mock(Beetle.class);

		ArrayList<IBodyPart> emptyBodyPartCollection = new ArrayList<>(0);
		given(mockBeetle.getBodyParts()).willReturn(emptyBodyPartCollection);


		// when
		boolean result = body.canAppendToBeetle(mockBeetle);

		// then
		assertThat(result, is(true));
	}

	@Test
	public void shouldThrowExceptionWhenBeetleAlreadyHasBody() throws InvalidBodyPartSequence {
		// given
		Beetle mockBeetle = mock(Beetle.class);

		ArrayList<IBodyPart> justABodyCollection = new ArrayList<>(1);
		justABodyCollection.add(new Body());
		given(mockBeetle.getBodyParts()).willReturn(justABodyCollection);


		// when
		String errorMsg = null;
		try {
			boolean result = body.canAppendToBeetle(mockBeetle);
		} catch (InvalidBodyPartSequence e) {
			errorMsg = e.getMessage();
		}

		// then
		assertThat(errorMsg, notNullValue());
		assertThat(errorMsg, equalTo("The Beetle already has a body!"));
	}
}
