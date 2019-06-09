/**
 * A runway dedicated to choppers
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.AbstractMediator;
import ch.heig.ui.TowerControlType;

public class ChopperRunway extends Runway {

    public ChopperRunway(String identifier, int spaces, AbstractMediator mediator) {
        super(identifier, spaces, TowerControlType.CHOPPER, mediator);
    }
}
