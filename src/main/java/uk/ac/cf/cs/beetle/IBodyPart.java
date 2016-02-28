package uk.ac.cf.cs.beetle;


import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

interface IBodyPart {
	boolean canAppendToBeetle(Beetle beetle) throws InvalidBodyPartSequence;
	void accept(BeetleRenderer visitor);
}
