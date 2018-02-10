package virtualpetsamok;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class VirtualPetShelter {
	
	String description;
	Map<String, VirtualPet> petList = new HashMap<>();

	public VirtualPetShelter() {
		this("Acme Pet Shelter");
	}

	public VirtualPetShelter(String description) {
		setDescription(description);
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public void intake(VirtualPet pet) {
		petList.put(pet.getName(), pet);
	}

	public VirtualPet getPetByName(String petName) {
		return petList.get(petName);
	}

	public Collection<VirtualPet> getAllPets() {
		return petList.values();
	}

	public void adopt(String petName) {
		petList.remove(petName);
	}

//	public void feedAllPets() {
//		petList.values().forEach(VirtualPet::feed);
//	}
//
//	public void waterAllPets() {
//		petList.values().forEach(VirtualPet::water);
//	}
//
//	public void playWithAllPets() {
//		petList.values().forEach(VirtualPet::playWith);
//	}
//
//	public void tick() {
//		petList.values().forEach(VirtualPet::tick);
//	}

	@Override
	public String toString() {
		return description;
	}

}
