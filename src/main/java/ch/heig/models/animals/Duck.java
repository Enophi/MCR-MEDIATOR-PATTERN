/**
 * A bird that lives by water and has webbed feet, a short neck, and a large beak
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.animals;

import ch.heig.mediator.AbstractMediator;

public class Duck extends Animal {

    public Duck(AbstractMediator mediator) {
        super("DuckDuckGo", mediator);
    }
}
