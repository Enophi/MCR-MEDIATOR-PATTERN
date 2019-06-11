/**
 * A runway dedicated to choppers
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.time.AbstractTimeMediator;
import ch.heig.ui.FlyingObjectType;

/**
 * The type Chopper runway.
 */
public class ChopperRunway extends Runway {

    /**
     * Instantiates a new Chopper runway.
     *
     * @param identifier the identifier
     * @param mediator   the mediator
     */
    public ChopperRunway(String identifier, AbstractTimeMediator mediator) {
        super(identifier, FlyingObjectType.CHOPPER, mediator);
    }
}
