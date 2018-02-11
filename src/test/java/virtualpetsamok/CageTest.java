package virtualpetsamok;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class CageTest {
	private Cage cageBoxUnderBox = new Cage();
	private VirtualPet testPet1 = new VirtualPetDog("Fido", "a Mutt");

	private VirtualPet testPet2 = new VirtualPetDog("Spot", "a dirtball");
	@Test
	public void testAddOneOkay()  {
		cageBoxUnderBox.add(testPet1);
		assertThat(cageBoxUnderBox.getOccupancy(), is(1));	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddTwoAndGetException()  {
		cageBoxUnderBox.add(testPet1);
		cageBoxUnderBox.add(testPet2);
	}

}
