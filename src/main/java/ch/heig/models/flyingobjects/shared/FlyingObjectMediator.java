package ch.heig.models.flyingobjects.shared;

import ch.heig.mediator.time.AbstractTimeMediator;
import ch.heig.models.runways.Runway;

public interface FlyingObjectMediator {

    void askToLand(Runway runway);

    void selfAnnounce();

    void setMediator(AbstractTimeMediator mediator);
}
