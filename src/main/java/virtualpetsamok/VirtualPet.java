package virtualpetsamok;

public abstract class VirtualPet {

	protected final static int INITIAL_HEALTH = 50;
	protected final static int INITIAL_BOREDOM = 40;
	protected final static int INITIAL_HAPPINESS = 53;

	private String description;
	private String name;
	private int boredom;
	private int health;
	private int happiness;

	public VirtualPet(String name, String description) {
		this(name, description, INITIAL_HEALTH, INITIAL_BOREDOM, INITIAL_HAPPINESS);
	}

	public VirtualPet(String name, String description, int health, int boredom, int happiness) {
		this.name = name;
		this.description = description;
		this.setHealth(health);
		this.setBoredom(boredom);
		this.setHappiness(happiness);
	}

	public void enviromentHealthUpdate(int EnviromentCleanliness) {
		int magicMaxHealthDrop = 20;
		int healthDrop = (magicMaxHealthDrop * (100 - EnviromentCleanliness) / 100);
		setHealth(Math.max(0, getHealth() - healthDrop));
	}

	public int getBoredom() {
		return boredom;
	}

	public String getDescription() {
		return description;
	}

	public int getHappiness() {
		return happiness;
	}

	public int getHealth() {
		return health;
	}

	public String getName() {
		return name;
	}

	public void playWith() {
		setBoredom(getBoredom() - 5);
	}

	public void setBoredom(int boredom) {
		this.boredom = boredom;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int tick() {
		setBoredom(getBoredom() + 2);
		setHappiness(getHappiness() - 1);
		setHealth(getHealth() - 1);
		return 0;
	}

}
