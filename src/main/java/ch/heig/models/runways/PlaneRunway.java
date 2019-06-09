/**
 * A runway dedicated to planes
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.runways;

import ch.heig.mediator.AbstractMediator;
import ch.heig.ui.TowerControlType;

public class PlaneRunway extends Runway {

    public PlaneRunway(String identifier, int spaces, TowerControlType type, AbstractMediator mediator) {
        super(identifier, spaces, type, mediator);
    }

}
