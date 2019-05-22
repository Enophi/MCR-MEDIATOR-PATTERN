/**
 * created by Aleksandar Milenkovic
 * 08.05.2019
 * 16:54
 */

package ch.heig.models.runways;

import ch.heig.models.animals.Animal;

import java.util.List;

public abstract class AbstractRunway {
    private List<Animal> animals;

    public abstract void clean();
}
