/**
 * An HEIG teacher
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.animals;

import ch.heig.models.runways.Runway;

/**
 * The type Pier.
 */
public class Pier extends Animal {

    /**
     * Instantiates a new Pier.
     *
     * @param runway the runway
     */
    public Pier(Runway runway) {
        super("Pier", runway);
    }

    /**
     * The boss is coming to the strip
     */
    @Override
    public void selfAnnounce() {
        super.selfAnnounce();
    }

}
