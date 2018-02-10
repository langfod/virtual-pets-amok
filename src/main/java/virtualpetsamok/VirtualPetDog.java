package virtualpetsamok;

public class VirtualPetDog extends BagOfMostlyWater implements Walkable {

	protected static final int WALK_HAPPINESS_DELTA = 12;
	protected static final int WALK_POOPINESS_DELTA = 13;
	protected static final int WALK_BOREDOM_DELTA = 14;

	
	public VirtualPetDog(String name, String description, int health, int boredom, int hunger, int thirst,
			int poopiness, int happiness) {
		super(name, description, health, boredom, hunger, thirst, poopiness, happiness );
	}

//	public VirtualPetDog(String name, String description, int health, int boredom) {
//		super(name, description, health, boredom);
//	}

	public VirtualPetDog(String name, String description) {
		super(name, description);
	}

	@Override
	public void walk() {
		// TODO Still needs soil fiddlybit
		setHappiness(getHappiness() + WALK_HAPPINESS_DELTA);
		setPoopiness(getPoopiness() - WALK_POOPINESS_DELTA);
		setBoredom(getBoredom() - WALK_BOREDOM_DELTA);
	}

}