package virtualpetsamok;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RoboticCatTest {

	private static RoboticCat petUnderTest = new RoboticCat("Pet Name", "Pet Desciption", 50, 99, 80, 75);

	private VirtualPet petUnderTestShort = new RoboticCat("Pet Name", "Pet Description");

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
		petUnderTest = new RoboticCat("Pet Name", "Pet Desciption", 50, 99, 80, 75);
		assertThat(petUnderTest.getHealth(), is(50));
		assertThat(petUnderTest.getBoredom(), is(99));
		assertThat(petUnderTest.getHappiness(), is(80));
		assertThat(petUnderTest.getOilLevel(), is(75));
	}

	@Test
	public void oilShouldChangeByDeltaValue() {
		int oilBefore = petUnderTest.getOilLevel();
		petUnderTest.oil();
		int oilChanged = petUnderTest.getOilLevel() - oilBefore;
		assertThat(oilChanged, is(MechanizedEntity.OIL_DELTA));
	}

}
