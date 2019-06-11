/**
 * A runway dedicated to planes
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.time.AbstractTimeMediator;
import ch.heig.ui.FlyingObjectType;

/**
 * The type Plane runway.
 */
public class PlaneRunway extends Runway {

    /**
     * Instantiates a new Plane runway.
     *
     * @param identifier the identifier
     * @param mediator   the mediator
     */
    public PlaneRunway(String identifier, AbstractTimeMediator mediator) {
        super(identifier, FlyingObjectType.PLANE, mediator);
    }

}
