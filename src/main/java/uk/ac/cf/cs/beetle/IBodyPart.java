package uk.ac.cf.cs.beetle;


interface IBodyPart {
	boolean canAppendToBeetle(Beetle beetle) throws InvalidBodyPartSequence;
}
