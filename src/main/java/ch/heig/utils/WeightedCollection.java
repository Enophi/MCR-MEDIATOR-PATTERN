package ch.heig.utils;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * The type Weighted collection.
 *
 * @param <E> the type parameter
 */
public class WeightedCollection<E> {
	
	private NavigableMap<Integer, E> map = new TreeMap<Integer, E>();
	private Random random;
	private int total = 0;

    /**
     * Instantiates a new Weighted collection.
     */
    public WeightedCollection() {
		this(new Random());
	}

    /**
     * Instantiates a new Weighted collection.
     *
     * @param random the random
     */
    public WeightedCollection(Random random) {
		this.random = random;
	}

    /**
     * Add.
     *
     * @param weight the weight
     * @param object the object
     */
    public void add(int weight, E object) {
		if (weight <= 0) return;
		total += weight;
		map.put(total, object);
	}

    /**
     * Next e.
     *
     * @return the e
     */
    public E next() {
		int value = random.nextInt(total) + 1; // Can also use floating-point weights
		return map.ceilingEntry(value).getValue();
	}
	
}
