/**
 * A long, level piece of ground with a specially prepared smooth, hard surface on which aircraft take off and land
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.AbstractMediator;
import com.almasb.fxgl.entity.component.Component;

public abstract class Runway extends Component {

    private final String identifier;
    private int spaces;
    private boolean isOpen;
    private AbstractMediator mediator;

    public Runway(String identifier, int spaces, boolean isOpen, AbstractMediator mediator) {
        this.identifier = identifier;
        this.spaces = spaces;
        this.isOpen = isOpen;
        this.mediator = mediator;
        mediator.selfAnnounce(this);
    }

    public int getSpaces() {
        return spaces;
    }

    public void setSpaces(int spaces) {
        this.spaces = spaces;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public boolean setOpen(boolean open) {
        this.isOpen = open;
        return isOpen;
    }

    public AbstractMediator getMediator() {
        return mediator;
    }

    public void setMediator(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
