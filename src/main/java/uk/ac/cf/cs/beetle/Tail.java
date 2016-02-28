package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

public class Tail implements IBodyPart {
    @Override
    public boolean canAppendToBeetle(Beetle beetle) throws InvalidBodyPartSequence {
        boolean doesntHaveBody = beetle.getBodyParts().stream()
                .map(bodyPart -> bodyPart.getClass())
                .anyMatch(bpClass -> bpClass == Body.class);

        boolean alreadyHasTail = beetle.getBodyParts().stream()
                .map(bodyPart -> bodyPart.getClass())
                .anyMatch(bpClass -> bpClass == Tail.class);

        if (doesntHaveBody) {
            throw new InvalidBodyPartSequence("The beetle needs a Body to have a Tail");
        }

        if (alreadyHasTail) {
            throw new InvalidBodyPartSequence("The beetle already has a Tail");
        }

        return true;
    }

    @Override
    public void accept(BeetleRenderer visitor) {
        visitor.visit(this);
    }
}
