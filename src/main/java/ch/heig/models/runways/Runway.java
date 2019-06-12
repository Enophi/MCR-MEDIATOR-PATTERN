/**
 * A long, level piece of ground with a specially prepared smooth, hard surface on which aircraft take off and land
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.time.AbstractTimeMediator;
import ch.heig.ui.FlyingObjectType;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Runway.
 */
public abstract class Runway extends Component {
    private double maxPlaces = 1.0;

    /**
     * The livingObjectss.
     */
    protected List<Entity> livingObjects;

    private final FlyingObjectType type;
    private AbstractTimeMediator mediator;

    private final String identifier;

    /**
     * Instantiates a new Runway.
     *
     * @param identifier the identifier
     * @param type       the type
     * @param mediator   the mediator
     */
    public Runway(String identifier, FlyingObjectType type, AbstractTimeMediator mediator) {
        this.identifier = identifier;
        this.type = type;
        this.mediator = mediator;
        livingObjects = new LinkedList<>();
    }

    /**
     * Getter of strip capacity
     *
     * @return number of place
     */
    public double getMaxPlaces() {
        return maxPlaces;
    }

    /**
     * Sets max places.
     *
     * @param maxPlaces the max places
     */
    public void setMaxPlaces(double maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    /**
     * Getter of type d'objects
     *
     * @return type of objects accepted
     */
    public FlyingObjectType getType() {
        return type;
    }

    /**
     * Tell us if the strip is obstructing by the livingObjects
     *
     * @return the result
     */
    public boolean isBlocked() {
        return !livingObjects.isEmpty();
    }

    /**
     * Tell us how many livingObjects are on the strip
     *
     * @return numbers of livingObjects
     */
    public int getNumberOfLivingObjects() {
        return livingObjects.size();
    }

    /**
     * Self announce.
     *
     * @param livingObject the livingObjects
     */
    public void selfAnnounce(Entity livingObject) {
        livingObjects.add(livingObject);
    }

    /**
     * Remove the game entity of the list
     *
     * @param e The entity to remove
     */
    public void selfDestroy(Entity e) {
        this.livingObjects.remove(e);
    }

    /**
     * delete all the livingObjects
     */
    public void destroyAll() {
        for (Entity e : livingObjects)
            e.removeFromWorld();
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
