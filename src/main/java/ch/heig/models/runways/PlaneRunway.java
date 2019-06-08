/**
 * A runway dedicated to planes
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.AbstractMediator;

public class PlaneRunway extends Runway {

    public PlaneRunway(int spaces, boolean isOpen, AbstractMediator mediator) {
        super(spaces, isOpen, mediator);
    }

}
