package virtualpetsamok;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public abstract class PetHolder implements Iterable<Entry<String, VirtualPet>> {
	int cleanliness = 100;
	int capacity;

	Map<String, VirtualPet> thingOfHolding = new HashMap<>();

	public PetHolder(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public Iterator<Entry<String, VirtualPet>> iterator() {
		return thingOfHolding.entrySet().iterator();
	}

	public void clean() {
		this.cleanliness = 100;
	}

	public void dirty() {
		dirty(1);
	}

	public void dirty(int dirtiness) {
		cleanliness -= dirtiness;
	}

	public int getCleanliness() {
		return cleanliness;
	}

	public boolean isSpaceAvailable() {
		return (getOccupancy() < capacity);
	}

	public void add(VirtualPet p) {
		if (getOccupancy() >= capacity)
			throw new IndexOutOfBoundsException();
		thingOfHolding.put(p.getName(), p);
	}

	public VirtualPet remove(VirtualPet p) {
		return thingOfHolding.remove(p.getName());
	}

	public int getOccupancy() {
		return thingOfHolding.size();
	}

	public Collection<VirtualPet> getOccupants() {
		return thingOfHolding.values();
	}

}
