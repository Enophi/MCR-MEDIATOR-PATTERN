/**
 * A creature with feathers and wings, usually able to fly
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.animals;

import ch.heig.models.runways.Runway;

public class Bird extends Animal {

    public Bird(Runway runway) {
        super("Birdy", runway);
    }

    /**
     * Animal is coming to the strip
     */
    @Override
    public void selfAnnounce() {
        super.selfAnnounce();
    }
}
