/**
 * Something that lives and moves
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.animals;

import ch.heig.mediator.AbstractMediator;
import com.almasb.fxgl.entity.component.Component;


public abstract class Animal extends Component {

    private final String identifier;
    private AbstractMediator mediator;

    public Animal(String identifier, AbstractMediator mediator) {
        this.identifier = identifier;
        this.mediator = mediator;
        mediator.selfAnnounce(this);
    }

    public AbstractMediator getMediator() {
        return mediator;
    }

    public void setMediator(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String toString() {
        return identifier;
    }
}
