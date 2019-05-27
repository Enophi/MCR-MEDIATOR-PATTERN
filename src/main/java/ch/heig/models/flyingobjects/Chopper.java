/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 16:06
 */

package ch.heig.models.flyingobjects;

import ch.heig.mediator.AbstractMediator;

public class Chopper extends FlyingObject {

    public Chopper(AbstractMediator mediator) {
        super(mediator);
    }

    @Override
    public void askToLand(int runwayNo) {
        mediator.askToLand(this, runwayNo);
    }

    @Override
    public String identifier() {
        return "Chopper 456546";
    }
}
