/**
 * A long, level piece of ground with a specially prepared smooth, hard surface on which aircraft take off and land
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.AbstractMediator;
import ch.heig.ui.TowerControlType;
import com.almasb.fxgl.entity.component.Component;

public abstract class Runway extends Component {

    private final String identifier;
    private final int spaces;
    private final TowerControlType type;
    private boolean isOpen;
    private AbstractMediator mediator;

    public Runway(String identifier, int spaces, TowerControlType type, boolean isOpen, AbstractMediator mediator) {
        this.identifier = identifier;
        this.spaces = spaces;
        this.type = type;
        this.isOpen = isOpen;
        this.mediator = mediator;
        mediator.selfAnnounce(this);
    }

    public int getSpaces() {
        return spaces;
    }

    public TowerControlType getType() {
        return type;
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
