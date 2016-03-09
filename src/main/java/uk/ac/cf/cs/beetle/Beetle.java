package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

import java.beans.PropertyChangeListener;
import java.util.Collection;

public interface Beetle {
    Collection<IBodyPart> getBodyParts();
    void addBodyPart(IBodyPart bodyPart) throws InvalidBodyPartSequence;
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
