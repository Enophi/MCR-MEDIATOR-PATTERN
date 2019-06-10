/**
 * A runway dedicated to choppers
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.time.AbstractTimeMediator;
import ch.heig.ui.FlyingObjectType;

public class ChopperRunway extends Runway {

    public ChopperRunway(String identifier, AbstractTimeMediator mediator) {
        super(identifier, FlyingObjectType.CHOPPER, mediator);
    }
}
