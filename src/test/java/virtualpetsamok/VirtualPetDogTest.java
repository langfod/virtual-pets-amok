package virtualpetsamok;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VirtualPetDogTest {

	private static final VirtualPetDog petUnderTest = new VirtualPetDog("Pet Name", "Pet Desciption", 50, 99, 51, 52,
			20, 53);

	private VirtualPet petUnderTestShort = new VirtualPetDog("Pet Name", "Pet Description");

	@Test
	public void constructorNameAndDescription() {
		assertNotNull(new VirtualPetDog("Pet Name", "Pet Desciption"));
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
	// VirtualPet petUnderTest = new VirtualPetDog("Pet Name", "Pet Desciption", 50,
	// 99);
	// assertThat(petUnderTest.getBoredom(), is(99));
	// assertThat(petUnderTest.getHealth(), is(50));
	// }

	@Test
	public void constructorNameAndDescriptionAndFullAttributes() {
		assertThat(petUnderTest.getHunger(), is(51));
		assertThat(petUnderTest.getThirst(), is(52));
		assertThat(petUnderTest.getPoopiness(), is(20));
		assertThat(petUnderTest.getHappiness(), is(53));
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
	@Test
	public void soilShouldChangePoopinessByDeltaValue() {
		int poopinessBefore = petUnderTest.getPoopiness();
		petUnderTest.soil();
		int poopinessChanged = poopinessBefore - petUnderTest.getPoopiness();
		assertThat(poopinessChanged, is(BagOfMostlyWater.POOP_DELTA));
	}
	*/
}
