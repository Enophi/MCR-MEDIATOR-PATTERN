/**
 * A long, level piece of ground with a specially prepared smooth, hard surface on which aircraft take off and land
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.AbstractMediator;
import ch.heig.ui.FlyingObjectType;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import java.util.LinkedList;
import java.util.List;

public abstract class Runway extends Component {
    final double MAX_PLACES = 1.0;

    protected List<Entity> animals;

    private final FlyingObjectType type;
    private AbstractMediator mediator;

    private final String identifier;

    public Runway(String identifier, FlyingObjectType type, AbstractMediator mediator) {
        this.identifier = identifier;
        this.type = type;
        this.mediator = mediator;
        animals = new LinkedList<>();
    }

    /**
     * Getter of strip capacity
     * @return number of place
     */
    public double getSpaces() {
        return MAX_PLACES;
    }

    /**
     * Getter of type d'objects
     * @return type of objects accepted
     */
    public FlyingObjectType getType() {
        return type;
    }

    /**
     * Tell us if the strip is obstructing by the animals
     * @return the result
     */
    public boolean isBlocked() {
        return !animals.isEmpty();
    }

    /**
     * Tell us how many animals are on the strip
     * @return numbers of animals
     */
    public int getNumberOfAnimals() {
        return animals.size();
    }

    /**
     * @param animal
     */
    public void selfAnnounce(Entity animal) {
        animals.add(animal);
    }

    /**
     * Remove the game entity of the list
     *
     * @param e The entity to remove
     */
    public void selfDestroy(Entity e) {
        this.animals.remove(e);
    }

    /**
     * delete all the animals
     */
    public void destroyAll() {
        for (Entity e : animals)
            e.removeFromWorld();
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
