package virtualpetsamok;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class VirtualPetShelterTest {

	private VirtualPetShelter vpsUnderTest = new VirtualPetShelter();
	private VirtualPet testPet = new OrganicDog("Pet Name", "Pet Description");

	@Test
	public void createEmptyShelter() {
		assertThat(vpsUnderTest, isA(VirtualPetShelter.class));
	}

	@Test
	public void createShelterWithOneDogCage() {
		vpsUnderTest = new VirtualPetShelter("Test Shelter", 1, 0);
		assertThat(vpsUnderTest.placesToStuffPets.size(), is(1));
		assertThat(vpsUnderTest.placesToStuffPets.iterator().next().getClass(),
				is(CoreMatchers.<Class<?>>equalTo(Cage.class)));
	}

	@Test
	public void createShelterWithOneLitterBox() {
		vpsUnderTest = new VirtualPetShelter("Test Shelter", 0, 1);
		assertThat(vpsUnderTest.placesToStuffPets.size(), is(1));
		assertThat(vpsUnderTest.placesToStuffPets.iterator().next().getClass(),
				is(CoreMatchers.<Class<?>>equalTo(LitterBox.class)));
	}

	@Test
	public void defaultShelterShouldHaveTenCagesAndOneLitterBoxOr11() {
		assertThat(vpsUnderTest.placesToStuffPets.size(), is(11));
	}

	@Test
	public void allowIntakeofDogIntoCage() {
		vpsUnderTest = new VirtualPetShelter("Test Shelter", 1, 0);
		vpsUnderTest.intake(testPet);
		assertThat(vpsUnderTest.placesToStuffPets.iterator().next().getOccupants().iterator().next(), is(testPet));
	}

	@Test
	public void allowIntakeofDogIntoCageOnly() {
		vpsUnderTest = new VirtualPetShelter("Test Shelter", 1, 1);
		assertTrue(vpsUnderTest.intake(testPet));
		for (PetHolder holder : vpsUnderTest.placesToStuffPets) {
			if (holder instanceof Cage) {
				assertThat(holder.getOccupancy(), is(1));
			} else if (holder instanceof LitterBox) {
				assertThat(holder.getOccupancy(), is(0));
			}
		}
	}

	@Test
	public void allowIntakeofCatIntoLitterBoxOnly() {
		vpsUnderTest = new VirtualPetShelter("Test Shelter", 1, 1);
		vpsUnderTest.intake(new OrganicCat("Test Cat", "meow"));
		for (PetHolder holder : vpsUnderTest.placesToStuffPets) {
			if (holder instanceof Cage) {
				assertThat(holder.getOccupancy(), is(0));
			} else if (holder instanceof LitterBox) {
				assertThat(holder.getOccupancy(), is(1));
			}
		}
	}

	@Test
	public void doubleCheckthatDirtyWorks() {
		vpsUnderTest.placesToStuffPets.forEach(h -> h.dirty(10));
		for (PetHolder holder : vpsUnderTest.placesToStuffPets) {
			assertThat(holder.getCleanliness(), is(90));
		}
	}

	@Test
	public void cleanAllCagesShouldSetCleanlinessto100CagesOnly() {
		vpsUnderTest.placesToStuffPets.forEach(h -> h.dirty(10));
		vpsUnderTest.cleanAllCages();
		for (PetHolder holder : vpsUnderTest.placesToStuffPets) {
			if (holder instanceof Cage) {
				assertThat(holder.getCleanliness(), is(100));
			} else if (holder instanceof LitterBox) {
				assertThat(holder.getCleanliness(), is(90));
			}

		}
	}

	@Test
	public void cleanAllLitterBoxesShouldSetCleanlinessto100LitterBoxesOnly() {
		vpsUnderTest.placesToStuffPets.forEach(h -> h.dirty(10));
		vpsUnderTest.emptyLitterBox();
		for (PetHolder holder : vpsUnderTest.placesToStuffPets) {
			if (holder instanceof Cage) {
				assertThat(holder.getCleanliness(), is(90));
			} else if (holder instanceof LitterBox) {
				assertThat(holder.getCleanliness(), is(100));
			}

		}
	}

	@Test
	public void returnsCollectionOfVitualPets() {
		assertThat(vpsUnderTest.getAllPets(), is(instanceOf(Collection.class)));
	}

	@Test
	public void intakePetandRetrieveItByName() {
		vpsUnderTest.intake(testPet);
		VirtualPet retrievedTestPet = vpsUnderTest.getPetByName("Pet Name");
		assertThat(testPet, is(retrievedTestPet));
	}

	@Test
	public void shouldAllowIntakeOfPetandRetrieveCollection() {
		vpsUnderTest.intake(testPet);
		Collection<VirtualPet> retrievedCollection = vpsUnderTest.getAllPets();
		assertThat(1, is(retrievedCollection.size()));
	}

	/*
	 * @Test public void shouldAllowIntakeOfMultiplePetsandRetrieveCollection() {
	 * vpsUnderTest.intake(testPet); vpsUnderTest.intake(new
	 * OrganicDog("Second Pet Name", "Second Description")); vpsUnderTest.intake(new
	 * OrganicDog("Third Pet Name", "Second Description")); Collection<VirtualPet>
	 * retrievedCollection = vpsUnderTest.getAllPets(); assertThat(3,
	 * is(retrievedCollection.size())); }
	 */

	@Test
	public void adoptShouldReturnEmptyOptionalWhenPetNotFound() {
		vpsUnderTest.intake(testPet);
		assertFalse(vpsUnderTest.adopt("Test Name").isPresent());
	}

	@Test
	public void shouldAllowAdoptionOfPetByName() {
		vpsUnderTest.intake(testPet);
		VirtualPet adoptedPet = vpsUnderTest.adopt("Pet Name").get();
		assertThat(adoptedPet, is(testPet));
	}

	@Test
	public void adoptShouldReduceNumberOfPets() {
		vpsUnderTest.intake(testPet);
		vpsUnderTest.intake(new OrganicDog("Pet Name2", "Pet Description"));
		int beforeSize = vpsUnderTest.getAllPets().size();
		vpsUnderTest.adopt("Pet Name").get();
		int afterSize = vpsUnderTest.getAllPets().size();
		assertThat(afterSize, lessThan(beforeSize));
	}

	@Test
	public void feedAllPetsInShelter() {
		vpsUnderTest.intake(testPet);
		testPet = new OrganicDog("Second Pet Name", "Second Description");
		vpsUnderTest.intake(testPet);
		int beforeHunger = ((BagOfMostlyWater) testPet).getHunger();
		vpsUnderTest.intake(new OrganicDog("Third Pet Name", "Second Description"));
		vpsUnderTest.feedAllPets();
		BagOfMostlyWater retrievedPet = (BagOfMostlyWater) vpsUnderTest.getPetByName("Second Pet Name");
		assertThat(retrievedPet.getHunger(), lessThan(beforeHunger));

	}

	@Test
	public void waterAllPetsInShelter() {
		vpsUnderTest.intake(testPet);
		testPet = new OrganicDog("Second Pet Name", "Second Description");
		vpsUnderTest.intake(testPet);
		int beforeThirst = ((BagOfMostlyWater) testPet).getThirst();
		vpsUnderTest.intake(new OrganicDog("Third Pet Name", "Second Description"));
		vpsUnderTest.waterAllPets();
		BagOfMostlyWater retrievedPet = (BagOfMostlyWater) vpsUnderTest.getPetByName("Second Pet Name");
		assertThat(retrievedPet.getThirst(), lessThan(beforeThirst));

	}

}
