/*
 * RoboticCat:
 * 
 * Subclass of BagOfMostlyWater class to hold attributes and actions for robotic virtual pet cats. 
 * implements the LitterBoxUser interface
 * 
 * Author: David Langford
 * Date  : Feb 09, 2018
 * 
 * 
 * Depends on:
 * VirtualPet
 * MechanizedEntity
 * LitterBoxUser
 * 
 */
package virtualpetsamok;

public class RoboticCat extends MechanizedEntity implements LitterBoxUser {

	public RoboticCat(String name, String description) {
		super(name, description);
	}

	public RoboticCat(String name, String description, int health, int boredom, int happiness, int oilLevel) {
		super(name, description, health, boredom, happiness, oilLevel);
	}

}
