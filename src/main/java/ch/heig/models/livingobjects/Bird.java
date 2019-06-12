/**
 * A creature with feathers and wings, usually able to fly
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.livingobjects;

import ch.heig.models.runways.Runway;

/**
 * The type Bird.
 */
public class Bird extends LivingObject {

    /**
     * Instantiates a new Bird.
     *
     * @param runway the runway
     */
    public Bird(Runway runway) {
        super("Birdy", runway);
    }

    /**
     * LivingObject is coming to the strip
     */
    @Override
    public void selfAnnounce() {
        super.selfAnnounce();
    }
}
