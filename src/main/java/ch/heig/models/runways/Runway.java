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

    private static int nextID = 1;

    private AbstractMediator mediator;
    private final int ID;
    private int spaces;
    private boolean isOpen;

    public Runway(int spaces, boolean isOpen, AbstractMediator mediator) {
        this.ID = nextID++;
        this.spaces = spaces;
        this.isOpen = isOpen;
        this.mediator = mediator;
        mediator.selfAnnounce(this);
    }

    public int getID() {
        return ID;
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

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public AbstractMediator getMediator() {
        return mediator;
    }

    public void setMediator(AbstractMediator mediator) {
        this.mediator = mediator;
    }
}
