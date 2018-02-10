package virtualpetsamok;

public abstract class BagOfMostlyWater extends VirtualPet {

	protected static final int INITIAL_POOPINESS = 25;
	protected static final int INITIAL_THIRST = 51;
	protected static final int INITIAL_HUNGER = 42;
	protected static final int FEED_DELTA = 10;
	protected static final int WATER_DELTA = 11;
	protected static final int POOP_DELTA = 12;

	private int poopiness;
	private int thirst;
	private int hunger;

	public BagOfMostlyWater(String name, String description) {
		this(name, description, INITIAL_HEALTH, INITIAL_BOREDOM, INITIAL_HUNGER, INITIAL_THIRST, INITIAL_POOPINESS, INITIAL_HAPPINESS);
	}

	
	public BagOfMostlyWater(String name, String description, int health, int boredom, int hunger, int thirst,
			int poopiness, int happiness) {
		super(name, description, health, boredom, happiness);
		this.setHunger(hunger);
		this.setThirst(thirst);
		this.setPoopiness(poopiness);
		this.setHappiness(happiness);
	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	public int getThirst() {
		return thirst;
	}

	public void setThirst(int thirst) {
		this.thirst = thirst;
	}

	public int getPoopiness() {
		return poopiness;
	}

	public void setPoopiness(int poopiness) {
		this.poopiness = poopiness;
	}

	public void feed() {
		setHunger(getHunger() - FEED_DELTA);
	}

	public void water() {
		setThirst(getThirst() - WATER_DELTA);
	}

	public void soil() {
		setPoopiness(getThirst() - POOP_DELTA);
	}
}