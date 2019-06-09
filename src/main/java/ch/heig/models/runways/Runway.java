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

import static ch.heig.utils.Rand.getRandomBool;

public abstract class Runway extends Component {

    private final String identifier;
    private final int spaces;
    private final TowerControlType type;
    private AbstractMediator mediator;

    public Runway(String identifier, int spaces, TowerControlType type, AbstractMediator mediator) {
        this.identifier = identifier;
        this.spaces = spaces;
        this.type = type;
        this.mediator = mediator;
        this.setOpen(getRandomBool());
    }

    public int getSpaces() {
        return spaces;
    }

    public TowerControlType getType() {
        return type;
    }

    public boolean setOpen(boolean open) {
        if (open) {
            mediator.selfAnnounce(this);
            return true;
        } else {
            mediator.selfDestroy(this);
            return false;
        }
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
