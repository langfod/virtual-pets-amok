package virtualpetsamok;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

public class VirtualPetShelterTest {

	private VirtualPetShelter vpsUnderTest = new VirtualPetShelter();
	private VirtualPet testPet = new VirtualPetDog("Pet Name", "Pet Description");

	@Test
	public void createEmptyShelter() {
		assertThat(vpsUnderTest, isA(VirtualPetShelter.class));
	}
	
	@Test public void returnsCollectionOfVitualPets() {
		assertThat(vpsUnderTest.getAllPets(), is(instanceOf(Collection.class)));
	}
	
	@Test public void allowIntakeofPet() {
		vpsUnderTest.intake(testPet);
	}
	
	@Test public void intakePetandRetrieveItByName() {
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
	@Test
	public void shouldAllowIntakeOfMultiplePetsandRetrieveCollection() {
		vpsUnderTest.intake(testPet);
		vpsUnderTest.intake(new VirtualPetDog("Second Pet Name", "Second Description"));
		vpsUnderTest.intake(new VirtualPetDog("Third Pet Name", "Second Description"));
		Collection<VirtualPet> retrievedCollection = vpsUnderTest.getAllPets();
		assertThat(3, is(retrievedCollection.size()));
	}

	@Test
	public void shouldAllowAdoptionOfPetByName() {
		vpsUnderTest.intake(testPet);
		vpsUnderTest.adopt("Test Name");
		Collection<VirtualPet> retrievedCollection = vpsUnderTest.getAllPets();
		assertThat(1, is(retrievedCollection.size()));
	}
}
