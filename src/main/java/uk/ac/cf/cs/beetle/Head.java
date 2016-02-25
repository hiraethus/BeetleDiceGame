package uk.ac.cf.cs.beetle;

public class Head implements IBodyPart {
    @Override
    public boolean canAppendToBeetle(Beetle beetle) throws InvalidBodyPartSequence {
        boolean hasBody = beetle.getBodyParts().stream().anyMatch(bp -> bp instanceof Body);

        if (! hasBody) {
            throw new InvalidBodyPartSequence("The beetle needs a Body to have a Head");
        }

        boolean hasHeadAlready = beetle.getBodyParts().stream().anyMatch(bp -> bp instanceof Head);
        if (hasHeadAlready) {
            throw new InvalidBodyPartSequence("The beetle already has a Head");
        }

        return true;
    }
}
