package uk.ac.cf.cs.beetle;


import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

public class Leg implements IBodyPart {
    private static final int MAX_NUM_LEGS = 6;


    @Override
    public boolean canAppendToBeetle(Beetle beetle) throws InvalidBodyPartSequence {
        boolean hasBody = beetle.getBodyParts().stream()
                .map(bodyPart -> bodyPart.getClass())
                .anyMatch(cls -> cls == Body.class);

        boolean hasMaxNumLegsAlready = beetle.getBodyParts().stream()
                .map(bodyPart -> bodyPart.getClass())
                .filter(cls -> cls == Leg.class)
                .count() == MAX_NUM_LEGS;

        if (!hasBody) {
            throw new InvalidBodyPartSequence("The beetle needs a Body to have a Leg");
        }

        if (hasMaxNumLegsAlready) {
            throw new InvalidBodyPartSequence(
                    String.format("The beetle already has %d legs", MAX_NUM_LEGS)
            );
        }

        return true;
    }

    @Override
    public void accept(BeetleRenderer visitor) {
        visitor.visit(this);
    }
}
