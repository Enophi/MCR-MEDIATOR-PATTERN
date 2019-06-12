/**
 * A bird that lives by water and has webbed feet, a short neck, and a large beak
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.livingobjects;

import ch.heig.models.runways.Runway;

/**
 * The type Duck.
 */
public class Duck extends LivingObject {

    /**
     * Instantiates a new Duck.
     *
     * @param runway the runway
     */
    public Duck(Runway runway) {
        super("DuckDuckGo", runway);
    }

    /**
     * LivingObject is coming to the strip
     */
    @Override
    public void selfAnnounce() {
        super.selfAnnounce();
    }
}
