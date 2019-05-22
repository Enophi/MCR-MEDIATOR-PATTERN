/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:47
 */

package ch.heig.mediator;

import ch.heig.models.flyingobjects.FlyingObject;
import ch.heig.models.runways.AbstractRunway;

import java.util.List;

public class DayMediator extends AbstractMediator {

    public DayMediator(List<FlyingObject> fo, List<AbstractRunway> ar) {
        super(fo, ar);
    }

    @Override
    String getBackgroundColor() {
        return "#fffb87";
    }


    void land() {

    }
}
