/**
 * A runway dedicated to planes
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.time.AbstractTimeMediator;
import ch.heig.ui.FlyingObjectType;

public class PlaneRunway extends Runway {

    public PlaneRunway(String identifier, AbstractTimeMediator mediator) {
        super(identifier, FlyingObjectType.PLANE, mediator);
    }

}
