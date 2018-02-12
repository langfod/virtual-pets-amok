package virtualpetsamok;

public class OrganicCat extends BagOfMostlyWater implements LitterBoxUser {

	private final int POOP_DELTA = 3;

	public OrganicCat(String name, String description) {
		super(name, description);
	}

	public OrganicCat(String name, String description, int health, int boredom, int happiness, int hunger, int thirst,
			int poopiness) {
		super(name, description, health, boredom, happiness, hunger, thirst, poopiness);
	}

	@Override
	public int soil() {
		int evacuationAmount = Math.min(getPoopiness(), POOP_DELTA);
		setPoopiness(getPoopiness() - evacuationAmount);
		return evacuationAmount;
	}

}
