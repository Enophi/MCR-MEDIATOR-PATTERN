/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:47
 */

package ch.heig.mediator;

import ch.heig.models.flyingobjects.FlyingObject;
import ch.heig.models.runways.AbstractRunway;
import javafx.scene.paint.Color;

import java.util.List;

public class DayMediator extends AbstractMediator {

    public DayMediator(List<FlyingObject> fo, List<AbstractRunway> ar) {
        super(fo, ar, Color.AZURE);
    }

    void land() {

    }
}
