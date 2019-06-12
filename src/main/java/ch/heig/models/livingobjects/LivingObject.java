/**
 * Something that lives and moves
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.livingobjects;

import ch.heig.models.runways.Runway;
import com.almasb.fxgl.entity.component.Component;

/**
 * The type LivingObject.
 */
public abstract class LivingObject extends Component {
    private final String identifier;
    private Runway runway;

    /**
     * Instantiates a new LivingObject.
     *
     * @param identifier the identifier
     * @param runway     the runway
     */
    public LivingObject(String identifier, Runway runway) {
        this.identifier = identifier;
        this.runway = runway;
    }

    /**
     * Getter of runway
     *
     * @return the runway where he is
     */
    public Runway getRunway() {
        return runway;
    }

    /**
     * LivingObject is coming to the strip
     */
    protected void selfAnnounce() {
        runway.selfAnnounce(getEntity());
    }

    /**
     * Is called when the object is destroyed
     * The strip is told to delete the livingObjects
     */
    @Override
    public void onRemoved() {
        super.onRemoved();
        runway.selfDestroy(getEntity());
    }

    /**
     * Identifier of the livingObjects
     *
     * @return identifier of the livingObjects
     */
    @Override
    public String toString() {
        return identifier;
    }
}
