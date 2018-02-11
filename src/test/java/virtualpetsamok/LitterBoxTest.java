package virtualpetsamok;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LitterBoxTest {

	private LitterBox litterBoxUnderBox = new LitterBox();
	private VirtualPet testPet1 = new VirtualPetDog("Fido", "a Mutt");
	private VirtualPet testPet2 = new VirtualPetDog("Spot", "a dirtball");

	@Test
	public void testLitterBox() {
		assertNotNull(litterBoxUnderBox);
	}

	@Test
	public void testAddAndgetOccupancy() {
		litterBoxUnderBox.add(testPet1);
		litterBoxUnderBox.add(testPet2);
		assertThat(litterBoxUnderBox.getOccupancy(), is(2));
	}

	@Test
	public void testAddAndgetOccupants() {
		litterBoxUnderBox.add(testPet1);
		litterBoxUnderBox.add(testPet2);
		assertThat(litterBoxUnderBox.getOccupants(), contains(testPet1, testPet2));
	}

	@Test
	public void testRemove() {
		litterBoxUnderBox.add(testPet1);
		litterBoxUnderBox.add(testPet2);
		litterBoxUnderBox.remove(testPet1);
		assertThat(litterBoxUnderBox.getOccupancy(), is(1));
	}

	@Test
	public void testDirtyWithValue() {
		int beforeClean = litterBoxUnderBox.getCleanliness();
		litterBoxUnderBox.dirty(42);
		assertThat(litterBoxUnderBox.getCleanliness(), is(beforeClean - 42));
	}

	@Test
	public void testDirtyWithOutValue() {
		int beforeClean = litterBoxUnderBox.getCleanliness();
		litterBoxUnderBox.dirty();
		assertThat(litterBoxUnderBox.getCleanliness(), lessThan(beforeClean));
	}

	@Test
	public void testClean() {
		int beforeClean = litterBoxUnderBox.getCleanliness();
		litterBoxUnderBox.dirty();
		litterBoxUnderBox.clean();
		assertThat(litterBoxUnderBox.getCleanliness(), lessThan(beforeClean));
	}

}
