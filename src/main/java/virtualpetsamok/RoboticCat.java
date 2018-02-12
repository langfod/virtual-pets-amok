package virtualpetsamok;

public class RoboticCat extends MechanizedEntity implements LitterBoxUser {

	public RoboticCat(String name, String description) {
		super(name, description);
	}

	public RoboticCat(String name, String description, int health, int boredom, int happiness, int oilLevel) {
		super(name, description, health, boredom, happiness, oilLevel);
	}

}
