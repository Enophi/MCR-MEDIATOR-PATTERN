/**
 * A runway dedicated to choppers
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.AbstractMediator;

public class ChopperRunway extends Runway {

    public ChopperRunway(String identifier, int spaces, boolean isOpen, AbstractMediator mediator) {
        super(identifier, spaces, isOpen, mediator);
    }
}
