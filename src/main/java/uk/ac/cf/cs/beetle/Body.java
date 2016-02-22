package uk.ac.cf.cs.beetle;

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
}
