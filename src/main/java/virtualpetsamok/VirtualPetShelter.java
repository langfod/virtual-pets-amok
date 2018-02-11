package virtualpetsamok;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class VirtualPetShelter {

	String description;
	// TODO Map<String, VirtualPet> petList = new HashMap<>();
	Collection<PetHolder> placesToStuffPets = new ArrayList<>();

	public VirtualPetShelter() {
		this("Acme Pet Shelter");
	}

	public VirtualPetShelter(String description) {
		this(description, 10, 1);
	}

	public VirtualPetShelter(String description, int numDogCages, int numCatLitterBoxes) {
		setDescription(description);
		addPetHoldersToShelter(numDogCages, Cage.class);
		addPetHoldersToShelter(numCatLitterBoxes, LitterBox.class);
	}

	private void addPetHoldersToShelter(int dogCages, Class<? extends PetHolder> holder) {
		Collections.nCopies(dogCages, 1).stream().forEach(c -> {
			try {
				placesToStuffPets.add(holder.newInstance());
			} catch (InstantiationException e) {
				System.err.println(e);
			} catch (IllegalAccessException e) {
				System.err.println(e);
			}
		});
	}

	// add pet to holder; If walkable then holder must be Cage
	// if no room is found then return False
	// if added then return True
	public boolean intake(VirtualPet pet) {
		boolean tookPet = false;
		if (pet instanceof Walkable) {
			Optional<PetHolder> cage = placesToStuffPets.stream().filter(Cage.class::isInstance)
					.filter(c -> c.isSpaceAvailable()).findFirst();
			if (cage.isPresent()) {
				cage.get().add(pet);
				tookPet = true;
			}
		}
		return tookPet;
	}

	public void cleanAllCages() {
		cleanPetHolder(Cage.class);
	}

	public void cleanAllLitterBox() {
		cleanPetHolder(LitterBox.class);
	}

	private void cleanPetHolder(Class<? extends PetHolder> holder) {
		placesToStuffPets.stream().filter(holder::isInstance).forEach(h -> h.clean());
	}

	private Optional<AbstractMap.SimpleEntry<PetHolder, VirtualPet>> findPetHolderByPetName(String petName) {
		Optional<AbstractMap.SimpleEntry<PetHolder, VirtualPet>> output = Optional.empty();

		for (PetHolder holder : placesToStuffPets) {
			Optional<VirtualPet> foundPet = holder.getOccupants().stream().filter(pet -> pet.getName().equals(petName))
					.findFirst();
			if (foundPet.isPresent()) {
				output = Optional
						.ofNullable(new AbstractMap.SimpleEntry<PetHolder, VirtualPet>(holder, foundPet.get()));
				break;
			}
		}
		return output;
	}

	public VirtualPet getPetByName(String petName) {
		return findPetHolderByPetName(petName).isPresent() ? findPetHolderByPetName(petName).get().getValue() : null;
	}

	public Collection<VirtualPet> getAllPets() {
		Collection<VirtualPet> petList = new ArrayList<>();
		placesToStuffPets.stream().forEach(h -> petList.addAll(h.getOccupants()));
		return petList;
	}

	public Optional<VirtualPet> adopt(String petName) {
		Optional<SimpleEntry<PetHolder, VirtualPet>> optPetHolder = findPetHolderByPetName(petName);
		Optional<VirtualPet> outFoundPet = Optional.empty();
		if (optPetHolder.isPresent()) {
			outFoundPet = Optional.ofNullable(optPetHolder.get().getValue());
			optPetHolder.get().getKey().remove(outFoundPet.get());
		}
		return outFoundPet;
	}

	public void feedAllPets() {
		getAllPets().stream().filter(BagOfMostlyWater.class::isInstance).forEach(p -> ((BagOfMostlyWater)p).feed());
	}
	
	
	 public void waterAllPets() {
			getAllPets().stream().filter(BagOfMostlyWater.class::isInstance).forEach(p -> ((BagOfMostlyWater)p).water());
	 }
	
//	 public void playWithAllPets() {
//	 petList.values().forEach(VirtualPet::playWith);
//	 }
//	
//	 public void tick() {
//	 petList.values().forEach(VirtualPet::tick);
//	 }

	private void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}

}
