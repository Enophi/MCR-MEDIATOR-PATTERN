/**
 * A bird that lives by water and has webbed feet, a short neck, and a large beak
 *
 * @author Thibaud ALT
 * @version 1.0
 */

package ch.heig.models.animals;

import ch.heig.models.runways.Runway;

public class Duck extends Animal {

    public Duck(Runway runway) {
        super("DuckDuckGo", runway);
    }

    /**
     * Animal is coming to the strip
     */
    @Override
    public void selfAnnounce() {
        super.selfAnnounce();
    }
}
