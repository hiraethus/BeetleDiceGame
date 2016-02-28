package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

import java.util.Collection;
import java.util.LinkedList;

public class DefaultBeetle implements Beetle {
    private Collection<IBodyPart> bodyParts = new LinkedList<>();

    @Override
    public Collection<IBodyPart> getBodyParts() {
        return bodyParts;
    }

    @Override
    public void addBodyPart(IBodyPart bodyPart) throws InvalidBodyPartSequence {
        if (bodyPart.canAppendToBeetle(this)) {
            this.bodyParts.add(bodyPart);
        }
    }
}
