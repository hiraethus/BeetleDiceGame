package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

public class Eye implements IBodyPart {
    private static final int MAX_NUM_EYES = 2;

    @Override
    public boolean canAppendToBeetle(Beetle beetle) throws InvalidBodyPartSequence {
        boolean hasHead = beetle.getBodyParts().stream()
                .map(bp -> bp.getClass())
                .anyMatch(cls -> cls == Body.class);

        boolean alreadyHasMaxEyes = beetle.getBodyParts().stream()
                .map(bp -> bp.getClass())
                .filter(cls -> cls == Eye.class)
                .count() == MAX_NUM_EYES;

        if (! hasHead) {
            throw new InvalidBodyPartSequence("The beetle needs a Body to have an Eye");
        }

        if (alreadyHasMaxEyes) {
            throw new InvalidBodyPartSequence(
                    String.format("The beetle already has %d eyes", MAX_NUM_EYES)
            );
        }

        return true;
    }

    @Override
    public void accept(BeetleRenderer visitor) {
        visitor.visit(this);
    }
}
