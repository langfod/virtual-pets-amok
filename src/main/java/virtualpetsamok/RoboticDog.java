/*
 * RoboticDog:
 * 
 * Subclass of BagOfMostlyWater class to hold attributes and actions for robotic virtual pet dogs. 
 * implements the Walkable interface
 * 
 * Author: David Langford
 * Date  : Feb 09, 2018
 * 
 * 
 * Depends on:
 * VirtualPet
 * MechanizedEntity
 * Walkable
 * 
 */
package virtualpetsamok;

public class RoboticDog extends MechanizedEntity implements Walkable {

	protected static final int WALK_HAPPINESS_DELTA = 12;
	protected static final int WALK_BOREDOM_DELTA = 14;

	public RoboticDog(String name, String description) {
		super(name, description);
	}

	public RoboticDog(String name, String description, int health, int boredom, int happiness, int oillevel) {
		super(name, description, health, boredom, happiness, oillevel);
	}

	@Override
	public void walk() {
		setHappiness(getHappiness() + WALK_HAPPINESS_DELTA);
		setBoredom(getBoredom() - WALK_BOREDOM_DELTA);
	}

}
