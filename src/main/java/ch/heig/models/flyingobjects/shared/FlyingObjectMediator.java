package ch.heig.models.flyingobjects.shared;

import ch.heig.mediator.time.AbstractTimeMediator;

public interface FlyingObjectMediator {

    void askToLand(int runway);

    void selfAnnounce();

    void setMediator(AbstractTimeMediator mediator);
}
