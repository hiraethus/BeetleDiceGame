package uk.ac.cf.cs.beetle;

import java.beans.PropertyChangeListener;

interface Die {
    int rollDie();
    void addActionListener(PropertyChangeListener listener);
    void removeActionListener(PropertyChangeListener listener);
}