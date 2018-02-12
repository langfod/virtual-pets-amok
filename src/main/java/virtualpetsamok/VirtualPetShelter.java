/*
 * VirtualPetShelter:
 * 
 * Object to hold VirtualPet objects and perform actions on them.
 *
 * 
 * Author: David Langford
 * Date  : Feb 09, 2018
 * 
 * 
 * Depends on:
 * 
 * VirtualPet
 * BagsOfMostlyWater
 * MechanizedEntity
 * PetHolder
 * Cage
 * LitterBox
 * LitterBoxUser
 * 
 * 
 */
package virtualpetsamok;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class VirtualPetShelter {

	String description;
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

	public Optional<VirtualPet> adopt(String petName) {
		Optional<SimpleEntry<PetHolder, VirtualPet>> optPetHolder = findPetHolderByPetName(petName);
		Optional<VirtualPet> outFoundPet = Optional.empty();
		if (optPetHolder.isPresent()) {
			outFoundPet = Optional.ofNullable(optPetHolder.get().getValue());
			optPetHolder.get().getKey().remove(outFoundPet.get());
		}
		return outFoundPet;
	}

	private boolean checkAndStuffPet(VirtualPet pet, Class<?> classTypeToFilter) {
		boolean tookPet = false;
		Optional<PetHolder> petHolder = placesToStuffPets.stream().filter(classTypeToFilter::isInstance)
				.filter(c -> c.isSpaceAvailable()).findFirst();
		if (petHolder.isPresent()) {
			petHolder.get().add(pet);
			tookPet = true;
		}
		return tookPet;
	}

	public void cleanAllCages() {
		cleanPetHolder(Cage.class);
	}

	private void cleanPetHolder(Class<? extends PetHolder> holder) {
		placesToStuffPets.stream().filter(holder::isInstance).forEach(h -> h.clean());
	}

	public void emptyLitterBox() {
		cleanPetHolder(LitterBox.class);
	}

	public void feedAllPets() {
		getAllPets().stream().filter(BagOfMostlyWater.class::isInstance).forEach(p -> ((BagOfMostlyWater) p).feed());
	}

	public Optional<AbstractMap.SimpleEntry<PetHolder, VirtualPet>> findPetHolderByPetName(String petName) {
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

	public Collection<VirtualPet> getAllPets() {
		Collection<VirtualPet> petList = new ArrayList<>();
		placesToStuffPets.stream().forEach(h -> petList.addAll(h.getOccupants()));
		return petList;
	}

	public VirtualPet getPetByName(String petName) {
		return findPetHolderByPetName(petName).isPresent() ? findPetHolderByPetName(petName).get().getValue() : null;
	}

	public boolean intake(VirtualPet pet) {
		boolean tookPet = false;
		if (pet instanceof Walkable) {
			tookPet = checkAndStuffPet(pet, Cage.class);
		} else if (pet instanceof LitterBoxUser) {
			tookPet = checkAndStuffPet(pet, LitterBox.class);
		}
		return tookPet;
	}

	public void oilAllRobots() {
		getAllPets().stream().filter(MechanizedEntity.class::isInstance).forEach(p -> ((MechanizedEntity) p).oil());
	}

	public void playWithPetByName(String petName) {
		getPetByName(petName).playWith();
	}

	public Collection<VirtualPet> removeDeadishPets() {
		Collection<VirtualPet> deadPets = new ArrayList<>();
		for (PetHolder holder : placesToStuffPets) {
			for (VirtualPet pet : holder.getOccupants()) {
				if (pet.getHealth() <= 1) {
					holder.remove(pet);
					deadPets.add(pet);
				}
			}
		}
		return deadPets;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public void tick() {
		for (PetHolder holder : placesToStuffPets) {
			for (VirtualPet pet : holder.getOccupants()) {
				int nightSoilAmount = pet.tick();
				if (pet instanceof BagOfMostlyWater) {
					holder.dirty(nightSoilAmount);
					pet.enviromentHealthUpdate(holder.getCleanliness());
				}
			}
		}
	}

	@Override
	public String toString() {
		return description;
	}

	public void waterAllPets() {
		getAllPets().stream().filter(BagOfMostlyWater.class::isInstance).forEach(p -> ((BagOfMostlyWater) p).water());
	}

}
