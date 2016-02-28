package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

public class Antenna implements IBodyPart {
    private static final int MAX_ANTENNA_COUNT = 2;

    @Override
    public boolean canAppendToBeetle(Beetle beetle) throws InvalidBodyPartSequence {
        boolean hasAHead = beetle.getBodyParts().stream()
                .map(bp -> bp.getClass())
                .anyMatch(cls -> cls == Head.class);

        boolean alreadyHasMaxAntenna = beetle.getBodyParts().stream()
                .map(bp -> bp.getClass())
                .filter(cls -> cls == Antenna.class)
                .count() == MAX_ANTENNA_COUNT;

        if (! hasAHead) {
            throw new InvalidBodyPartSequence("The beetle needs a Head to have an Antenna");
        }

        if (alreadyHasMaxAntenna) {
            throw new InvalidBodyPartSequence(
                    String.format("The beetle already has %d antennae", MAX_ANTENNA_COUNT)
            );
        }

        return true;
    }

    @Override
    public void accept(BeetleRenderer visitor) {
        visitor.visit(this);
    }
}
