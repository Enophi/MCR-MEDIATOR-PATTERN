/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 16:05
 */

package ch.heig.models.flyingobjects;

import ch.heig.mediator.AbstractMediator;

public class Plane extends FlyingObject {

    public Plane(AbstractMediator mediator) {
        super(mediator);
    }

    @Override
    public void askToLand(int runwayNo) {
        mediator.askToLand(this, runwayNo);
    }

    @Override
    public String identifier() {
        return "Plane #123";
    }
}
