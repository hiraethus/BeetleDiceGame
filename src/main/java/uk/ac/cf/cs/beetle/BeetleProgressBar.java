package uk.ac.cf.cs.beetle;


import javax.swing.JProgressBar;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BeetleProgressBar extends JProgressBar implements PropertyChangeListener {
    private final Beetle beetle;

    public BeetleProgressBar(Beetle beetle, int maxBodyParts) {
        super(0, maxBodyParts);
        this.beetle = beetle;
        this.beetle.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        int numBodyParts = this.beetle.getBodyParts().size();
        this.setValue(numBodyParts);
    }
}
