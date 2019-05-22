/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:48
 */

package ch.heig.mediator;

import ch.heig.models.flyingobjects.FlyingObject;
import ch.heig.models.runways.AbstractRunway;
import javafx.scene.paint.Color;

import java.util.List;

public class NightMediator extends AbstractMediator {

    public NightMediator(List<FlyingObject> fo, List<AbstractRunway> ar) {
        super(fo, ar, Color.DARKBLUE);
    }

    void land() {

    }
}
