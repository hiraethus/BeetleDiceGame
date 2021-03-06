package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

public class Body implements IBodyPart {
	@Override
	public boolean canAppendToBeetle(Beetle beetle) throws InvalidBodyPartSequence {
		boolean hasBodyAlready = beetle.getBodyParts().stream().anyMatch(b -> b instanceof Body);

		if (!hasBodyAlready) {
			return true;
		} else {
			throw new InvalidBodyPartSequence("The Beetle already has a body!");
		}
	}

	@Override
	public void accept(BeetleRenderer visitor) {
		visitor.visit(this);
	}
}
