package virtualpetsamok;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OrganicCatTest {

	private static OrganicCat petUnderTest = new OrganicCat("Pet Name", "Pet Desciption", 50, 99, 51, 52, 20, 53);

	private VirtualPet petUnderTestShort = new OrganicCat("Pet Name", "Pet Description");

	@Test
	public void constructorNameAndDescription() {
		assertNotNull(new OrganicCat("Pet Name", "Pet Desciption"));
	}

	@Test
	public void createVirtualPetObjectAndGetDescription() {
		assertThat(petUnderTestShort.getName(), is("Pet Name"));
	}

	@Test
	public void createVirtualPetObjectAndGetName() {
		assertThat(petUnderTestShort.getDescription(), is("Pet Description"));
	}

	@Test
	public void constructorNameAndDescriptionAndFullAttributes() {
		petUnderTest = new OrganicCat("Pet Name", "Pet Desciption", 50, 99, 51, 52, 20, 53);
		assertThat(petUnderTest.getHunger(), is(52));
		assertThat(petUnderTest.getThirst(), is(20));
		assertThat(petUnderTest.getPoopiness(), is(53));
		assertThat(petUnderTest.getHappiness(), is(51));
	}

	@Test
	public void feedShouldChangeByDeltaValue() {
		int hungerBefore = petUnderTest.getHunger();
		petUnderTest.feed();
		int hungerChanged = hungerBefore - petUnderTest.getHunger();
		assertThat(hungerChanged, is(BagOfMostlyWater.FEED_DELTA));
	}

	@Test
	public void waterShouldChangeByDeltaValue() {
		int thirstBefore = petUnderTest.getThirst();
		petUnderTest.water();
		int thirstChanged = thirstBefore - petUnderTest.getThirst();
		assertThat(thirstChanged, is(BagOfMostlyWater.WATER_DELTA));
	}

	@Test
	public void soilTestLowBoredom() {
		petUnderTest = new OrganicCat("Pet Name", "Pet Desciption", 50, 10, 51, 52, 20, 53);

		int soilAmount = petUnderTest.soil();
		assertThat(soilAmount, is(3));
	}

	@Test
	public void soilTestHighBoredom() {
		petUnderTest = new OrganicCat("Pet Name", "Pet Desciption", 50, 100, 51, 52, 100, 53);

		int soilAmount = petUnderTest.soil();
		
		assertThat(soilAmount, is(3));
	}
}
