package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

public class Tail implements IBodyPart {
    @Override
    public boolean canAppendToBeetle(Beetle beetle) throws InvalidBodyPartSequence {
        return ! beetle.getBodyParts().stream()
                .map(bodyPart -> bodyPart.getClass())
                .anyMatch(bpClass -> bpClass == Head.class);
    }

    @Override
    public void accept(BeetleRenderer visitor) {
        visitor.visit(this);
    }
}
