/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:46
 */

package ch.heig.mediator;

import ch.heig.models.flyingobjects.FlyingObject;
import ch.heig.models.runways.AbstractRunway;

import java.util.List;

public abstract class AbstractMediator {
    private List<FlyingObject> flyingObjects;
    private List<AbstractRunway> landingRunways;

    public AbstractMediator(List<FlyingObject> fo, List<AbstractRunway> lr) {
        flyingObjects = fo;
        landingRunways = lr;
    }

    abstract String getBackgroundColor();

    abstract void land();

}
