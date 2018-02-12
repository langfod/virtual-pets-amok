package virtualpetsamok;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OrganicDogTest {

	private static OrganicDog petUnderTest = new OrganicDog("Pet Name", "Pet Desciption", 50, 99, 51, 52, 20, 53);

	private VirtualPet petUnderTestShort = new OrganicDog("Pet Name", "Pet Description");

	@Test
	public void constructorNameAndDescription() {
		assertNotNull(new OrganicDog("Pet Name", "Pet Desciption"));
	}

	@Test
	public void createVirtualPetObjectAndGetDescription() {
		assertThat(petUnderTestShort.getName(), is("Pet Name"));
	}

	@Test
	public void createVirtualPetObjectAndGetName() {
		assertThat(petUnderTestShort.getDescription(), is("Pet Description"));

	}

	// @Test
	// public void constructorNameAndDescriptionAndBaseAttributes() {
	// VirtualPet petUnderTest = new OrganicDog("Pet Name", "Pet Desciption", 50,
	// 99);
	// assertThat(petUnderTest.getBoredom(), is(99));
	// assertThat(petUnderTest.getHealth(), is(50));
	// }

	@Test
	public void constructorNameAndDescriptionAndFullAttributes() {
		petUnderTest = new OrganicDog("Pet Name", "Pet Desciption", 50, 99, 51, 52, 20, 53);
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
	public void walkShouldDecreaseBoredomAndPoopinessAndIncreaseHappiness() {
		int boredomBefore = petUnderTest.getBoredom();
		int poopinessBefore = petUnderTest.getPoopiness();
		int happinessBefore = petUnderTest.getHappiness();
		petUnderTest.walk();
		assertThat(boredomBefore, greaterThan(petUnderTest.getBoredom()));
		assertThat(poopinessBefore, greaterThan(petUnderTest.getPoopiness()));
		assertThat(happinessBefore, lessThan(petUnderTest.getHappiness()));

	}
	/*
	 * @Test public void soilShouldChangePoopinessByDeltaValue() { int
	 * poopinessBefore = petUnderTest.getPoopiness(); petUnderTest.soil(); int
	 * poopinessChanged = poopinessBefore - petUnderTest.getPoopiness();
	 * assertThat(poopinessChanged, is(BagOfMostlyWater.POOP_DELTA)); }
	 */

	@Test
	public void soilTestLowBoredom() {
		petUnderTest = new OrganicDog("Pet Name", "Pet Desciption", 50, 10, 51, 52, 20, 53);

		int soilAmount = petUnderTest.soil();
		assertThat(soilAmount, is(12));
	}

	@Test
	public void soilTestHighBoredom() {
		petUnderTest = new OrganicDog("Pet Name", "Pet Desciption", 50, 100, 51, 52, 100, 53);

		int soilAmount = petUnderTest.soil();
		assertThat(petUnderTest.getBoredom(), is(100));
		assertThat(petUnderTest.getPoopiness(), is(13));
		assertThat(BagOfMostlyWater.POOP_DELTA, is(12));
		assertThat(soilAmount, is(40));
	}
}
