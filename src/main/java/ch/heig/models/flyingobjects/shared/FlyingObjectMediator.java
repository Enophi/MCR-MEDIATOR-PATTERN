package ch.heig.models.flyingobjects.shared;

import ch.heig.mediator.AbstractMediator;

public interface FlyingObjectMediator {

    void askToLand(int runway);

    void selfAnnounce();

    void setMediator(AbstractMediator mediator);
}
