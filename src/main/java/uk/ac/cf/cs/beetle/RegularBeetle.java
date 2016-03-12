package uk.ac.cf.cs.beetle;

import uk.ac.cf.cs.beetle.exception.InvalidBodyPartSequence;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;

public class RegularBeetle implements Beetle {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private Collection<IBodyPart> bodyParts = new ArrayList<>();
    public static final String ADDED_BODY_PART = "body part added";

	@Override
	public Collection<IBodyPart> getBodyParts() {
		return bodyParts;
	}

	@Override
	public void addBodyPart(IBodyPart bodyPart) throws InvalidBodyPartSequence {
        if (bodyPart.canAppendToBeetle(this)) {
            this.bodyParts.add(bodyPart);
            this.pcs.firePropertyChange(ADDED_BODY_PART, null, bodyPart);
        }
	}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
