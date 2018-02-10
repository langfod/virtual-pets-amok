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
		this(name,description,INITIAL_HEALTH,INITIAL_BOREDOM, INITIAL_HAPPINESS);
	}

	public VirtualPet(String name, String description, int health, int boredom, int happiness) {
		this.name = name;
		this.description = description;
		this.setHealth(health);
		this.setBoredom(boredom);
		this.setHappiness(happiness);
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public int getBoredom() {
		return boredom;
	}

	public void setBoredom(int boredom) {
		this.boredom = boredom;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}
	
	

}
