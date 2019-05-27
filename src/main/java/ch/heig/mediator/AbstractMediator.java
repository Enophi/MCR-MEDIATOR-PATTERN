/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 15:46
 */

package ch.heig.mediator;

import ch.heig.models.flyingobjects.FlyingObject;
import ch.heig.models.runways.AbstractRunway;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class AbstractMediator {

    private List<FlyingObject> flyingObjects;
    private List<AbstractRunway> landingRunways;
    private Color bgColor;

    public AbstractMediator(List<FlyingObject> fo, List<AbstractRunway> lr, Color bgColor) {
        flyingObjects = fo;
        landingRunways = lr;
    }

    public Color getBackgroundColor() {
        return bgColor;
    }

    abstract void askToLand(FlyingObject object, int piste);
}
