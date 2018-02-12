package virtualpetsamok;

public class OrganicDog extends BagOfMostlyWater implements Walkable {

	protected static final int WALK_HAPPINESS_DELTA = 12;
	protected static final int WALK_POOPINESS_DELTA = 13;
	protected static final int WALK_BOREDOM_DELTA = 14;

	public OrganicDog(String name, String description) {
		super(name, description);
	}

	public OrganicDog(String name, String description, int health, int boredom, int hunger, int thirst, int poopiness,
			int happiness) {
		super(name, description, health, boredom, hunger, thirst, poopiness, happiness);
	}

	@Override
	public int soil() {
		int magicBoredomThreshHold = 60;
		int currentPoopinessLevel = getPoopiness();

		int evacuationAmount = currentPoopinessLevel > POOP_DELTA ? POOP_DELTA : currentPoopinessLevel;

		evacuationAmount = Math.max(evacuationAmount, getBoredom() - magicBoredomThreshHold);
		evacuationAmount = Math.min(evacuationAmount, currentPoopinessLevel);
		setPoopiness(currentPoopinessLevel - evacuationAmount);
		return evacuationAmount;
	}

	@Override
	public void walk() {
		setHappiness(getHappiness() + WALK_HAPPINESS_DELTA);
		setPoopiness(getPoopiness() - WALK_POOPINESS_DELTA);
		setBoredom(getBoredom() - WALK_BOREDOM_DELTA);
	}
}
