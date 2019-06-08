package ch.heig.models.flyingobjects.shared;

import ch.heig.mediator.AbstractMediator;
import ch.heig.models.runways.Runway;

public interface FlyingObjectMediator {

    void askToLand(Runway runway);

    void selfAnnounce();

    void setMediator(AbstractMediator mediator);
}
