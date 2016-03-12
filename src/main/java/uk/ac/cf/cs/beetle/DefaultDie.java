package uk.ac.cf.cs.beetle;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

public class DefaultDie implements Die {
    private final Random dieNumberGenerator = new Random();
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    @Override
    public int rollDie() {
        int newVal = this.dieNumberGenerator.nextInt(6) + 1;
        propertyChangeSupport.firePropertyChange("DICE_ROLLED", -1, newVal);

        return newVal;
    }

    @Override
    public void addActionListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removeActionListener(PropertyChangeListener listener) {
        this.propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
