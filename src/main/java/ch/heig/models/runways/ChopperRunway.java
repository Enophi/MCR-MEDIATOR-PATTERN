/**
 * A runway dedicated to choppers
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.AbstractMediator;
import ch.heig.ui.FlyingObjectType;

public class ChopperRunway extends Runway {

    public ChopperRunway(String identifier, AbstractMediator mediator) {
        super(identifier, FlyingObjectType.CHOPPER, mediator);
    }
}
