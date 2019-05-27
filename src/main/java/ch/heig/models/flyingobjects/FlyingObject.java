/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 16:05
 */

package ch.heig.models.flyingobjects;

import ch.heig.mediator.AbstractMediator;
import com.almasb.fxgl.entity.Entity;


public abstract class FlyingObject extends Entity {
    AbstractMediator mediator;

    public FlyingObject(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void askToLand(int runwayNo);

    public abstract String identifier();

    public void setMediator(AbstractMediator mediator) {
        this.mediator = mediator;
    }


}
