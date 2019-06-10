/**
 * Something that lives and moves
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.animals;

import ch.heig.models.runways.Runway;
import com.almasb.fxgl.entity.component.Component;

public abstract class Animal extends Component {
    private final String identifier;
    private Runway runway;

    public Animal(String identifier, Runway runway) {
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
     * Animal is coming to the strip
     */
    protected void selfAnnounce() {
        runway.selfAnnounce(getEntity());
    }

    /**
     * Is called when the object is destroyed
     * The strip is told to delete the animal
     */
    @Override
    public void onRemoved() {
        super.onRemoved();
        runway.selfDestroy(getEntity());
    }

    /**
     * Identifier of the animal
     *
     * @return identifier of the animal
     */
    @Override
    public String toString() {
        return identifier;
    }
}
