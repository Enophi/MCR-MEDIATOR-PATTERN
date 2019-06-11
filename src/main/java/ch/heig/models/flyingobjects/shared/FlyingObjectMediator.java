package ch.heig.models.flyingobjects.shared;

import ch.heig.mediator.time.AbstractTimeMediator;
import ch.heig.models.runways.Runway;

/**
 * The interface Flying object mediator.
 */
public interface FlyingObjectMediator {

    /**
     * Ask to land.
     *
     * @param runway the runway
     */
    void askToLand(Runway runway);

    /**
     * Self announce.
     */
    void selfAnnounce();

    /**
     * Sets mediator.
     *
     * @param mediator the mediator
     */
    void setMediator(AbstractTimeMediator mediator);
}
