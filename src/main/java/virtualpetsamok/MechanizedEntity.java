/*
 * MechanizedEnity:
 * 
 * Abstract subclass of VirtualPet class to hold attributes and actions for robotic virtual pets. 
 *
 * 
 * Author: David Langford
 * Date  : Feb 09, 2018
 * 
 * 
 * Depends on:
 * VirtualPet
 * 
 */
package virtualpetsamok;

public abstract class MechanizedEntity extends VirtualPet {

	protected static final int INITIAL_OILLEVEL = 80;
	protected static final int OIL_DELTA = 11;

	private int oilLevel;

	public MechanizedEntity(String name, String description) {
		this(name, description, INITIAL_HEALTH, INITIAL_BOREDOM, INITIAL_HAPPINESS, INITIAL_OILLEVEL);
	}

	public MechanizedEntity(String name, String description, int health, int boredom, int happiness, int oillevel) {
		super(name, description, health, boredom, happiness);
		this.setOilLevel(oillevel);
	}

	public int getOilLevel() {
		return oilLevel;
	}

	private void setOilLevel(int oilLevel) {
		this.oilLevel = oilLevel;
	}

	public void oil() {
		setOilLevel(Math.min(100, getOilLevel() + OIL_DELTA));
		setHealth(Math.min(1000, getHealth() + OIL_DELTA));
	}

	@Override
	public int tick() {
		super.tick();
		setOilLevel(Math.max(0, getOilLevel() - OIL_DELTA / 2));
		int magicMaxHealthDrop = 20;
		int healthDrop = (magicMaxHealthDrop * (100 - getOilLevel()) / 100);
		setHealth(Math.max(0, getHealth() - healthDrop));
		return 0;
	}

}