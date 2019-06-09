/**
 * A creature with feathers and wings, usually able to fly
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.animals;

import ch.heig.mediator.AbstractMediator;

public class Bird extends Animal {

    public Bird(AbstractMediator mediator) {
        super("Birdy", mediator);
    }

}
