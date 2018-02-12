/*
 * VirtualPetShelter:
 * user interface to the VirtualPetShelter.
 * Have some fun with the shelter pet
 * 
 * Author: David Langford
 * Date  : Feb 02, 2018
 * 
 * 
 * Depends on:
 * 
 * VirtualPetShelter
 * VirtualPet
 * Menu
 * 
 * 
 */

package virtualpetsamok;

import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VirtualPetShelterAmokApp {

	private static Scanner input = new Scanner(System.in);
	private static VirtualPetShelter ArthursPetShelter = VirtualPetShelterFactory();

	public static void main(String[] args) {

		Menu mainMenu = buildMainMenu();

		System.out.println(createBanner(ArthursPetShelter));
		System.out.println(ArthursPetShelter.getAllPets().size());
		while (true) {
			System.out.println(createPetStatusReportString(ArthursPetShelter));
			System.out.println(mainMenu.genMenuString(2));
			mainMenu.getOrdinalMenuChoice(input).run();
			ArthursPetShelter.tick();
			// doCheckForStiffies();
		}

	}

	/*
	 * private static void doCheckForStiffies() { boolean stiffiesFound = false;
	 * Collection<VirtualPet> petShelterList = ArthursPetShelter.getAllPets();
	 * VirtualPet[] petsInShelter = new VirtualPet[petShelterList.size()];
	 * petsInShelter = petShelterList.toArray(petsInShelter);
	 * 
	 * for (VirtualPet pet : petsInShelter) { if (pet.getBoredom() > 100 ||
	 * pet.getHunger() > 100 || pet.getThirst() > 100) { System.out.println("Oops. "
	 * + pet.getName() + " has passed on."); ArthursPetShelter.adopt(pet.getName());
	 * stiffiesFound = true; } } if (stiffiesFound) { System.out.
	 * println("Their mortal remains have been sent to the incenerator.\n"); } else
	 * { System.out.println("\nFeel the sun shining and the birds a chirpin!\n"); }
	 * }
	 */
	private static Menu buildMainMenu() {
		Menu fpMenu = new Menu("Main Menu") {
			{
				addItem("Feed organic pets ", () -> doFeedPets(ArthursPetShelter));
				addItem("Water organic pets", () -> doWaterPets(ArthursPetShelter));
				addItem("Oil robotic pets  ", () -> doOilPets(ArthursPetShelter));
				addItem("Play with a pet   ", () -> doPlayWithaPet(ArthursPetShelter));
				addItem("Clean all cages   ", () -> doCleanAllCages(ArthursPetShelter));
				addItem("Empty LitterBox   ", () -> doEmptyLitterBox(ArthursPetShelter));
				addItem("Adopt a pet       ", () -> doAdoptaPet(ArthursPetShelter));
				addItem("Admit a pet       ", () -> doAdmissions(ArthursPetShelter));
				addItem("Quit              ", () -> goodBye());
			}

		};
		return fpMenu;
	}

	private static void doCleanAllCages(VirtualPetShelter vps) {
		vps.cleanAllCages();
	}

	private static void doEmptyLitterBox(VirtualPetShelter vps) {
		vps.emptyLitterBox();
	}

	private static Menu buildPetChoiceMenu() {
		Menu pcMenu = new Menu("Choose Pet", "splitkeyname");
		ArthursPetShelter.getAllPets()
				.forEach(p -> pcMenu.addItem(p.getName() + "," + p.getDescription(), () -> p.playWith()));
		return pcMenu;
	}

	private static void doFeedPets(VirtualPetShelter vps) {
		System.out.println("\nYou toss in scoops of chow, and run!\n");
		vps.feedAllPets();
	}

	@SuppressWarnings("serial")
	private static void doAdmissions(VirtualPetShelter vps) {
		System.out.println("\nAdmissions->");
		System.out.println("To admit a pet that has lost it's way, please fill out this form:\n");
		Map<String, Integer> attributeMap = new HashMap<String, Integer>();

		String intakeName;
		while (true) {
			System.out.println("What name does this pet identify as?");
			intakeName = input.nextLine();
			if (vps.getPetByName(intakeName) != null) {
				System.out.println("We already have a " + intakeName + ". This pet will have to be someone else.");
			} else {
				break;
			}
		}

		System.out.println("\nShort description of this pet:");
		String description = input.nextLine();

		String majorPetType;
		majorPetTypeLoop: while (true) {
			System.out.println("\nIs this pet a squishy [organic] pet, or a crunchy [robotic] pet?");
			String petType = input.nextLine();
			switch (petType.toLowerCase()) {
			case "organic":
				majorPetType = "organic";
				attributeMap.put("Health", 0);
				attributeMap.put("Boredom", 0);
				attributeMap.put("Happiness", 0);
				attributeMap.put("Hunger", 0);
				attributeMap.put("Thirst", 0);
				attributeMap.put("Poopiness", 0);
				break majorPetTypeLoop;
			case "robotic":
				majorPetType = "robotic";
				attributeMap.put("Health", 0);
				attributeMap.put("Boredom", 0);
				attributeMap.put("Happiness", 0);
				attributeMap.put("OilLevel", 0);
				break majorPetTypeLoop;
			default:
				System.out.println("Please enter either \"organic\" or \"robotic\":");
			}
		}

		String minorPetType;
		minorPetTypeLoop: while (true) {
			System.out.println("\nCurrently we can house either a [dog] or a [cat]. Which is yours?");
			String petType = input.nextLine();
			switch (petType.toLowerCase()) {
			case "dog":
				minorPetType = "dog";
				break minorPetTypeLoop;
			case "cat":
				minorPetType = "cat";
				break minorPetTypeLoop;
			default:
				System.out.println("Please enter either \"dog\" or \"cat\":");
			}
		}

		for (String attribute : attributeMap.keySet()) {
			boolean inputOkay = false;
			do {
				System.out.println("\nOn a scale of 0 to 100 please rate on this pets " + attribute + ":");
				int userInputNum;
				try {
					userInputNum = Integer.parseInt(input.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Please use value of 0 to 100.");
					continue;
				}
				if (userInputNum < 0 || userInputNum > 100) {
					System.out.println("Please use value of 0 to 100.");
				} else {
					attributeMap.put(attribute, userInputNum);
					inputOkay = true;
				}
			} while (!inputOkay);
		}
		VirtualPet newPet = virtualPetFactory(majorPetType, minorPetType, intakeName, description, attributeMap);
		if (!vps.intake(newPet)) {
			System.err.println("virtualPetFactory and intake are having issues.." + newPet.getName());
		}
	}

	private static VirtualPet virtualPetFactory(String majorPetType, String minorPetType, String name,
			String description, Map<String, Integer> attributeMap) {
		VirtualPet factoryBuiltPet = null;
		if (majorPetType.equalsIgnoreCase("organic")) {
			if (minorPetType.equalsIgnoreCase("dog")) {
				factoryBuiltPet = new OrganicDog(name, description, attributeMap.get("Health"),
						attributeMap.get("Boredom"), attributeMap.get("Happiness"), attributeMap.get("Hunger"),
						attributeMap.get("Thirst"), attributeMap.get("Poopiness"));
			} else if (minorPetType.equalsIgnoreCase("cat")) {
				factoryBuiltPet = new OrganicCat(name, description, attributeMap.get("Health"),
						attributeMap.get("Boredom"), attributeMap.get("Happiness"), attributeMap.get("Hunger"),
						attributeMap.get("Thirst"), attributeMap.get("Poopiness"));
			}
		} else if (majorPetType.equalsIgnoreCase("robotic")) {
			if (minorPetType.equalsIgnoreCase("dog")) {
				factoryBuiltPet = new RoboticDog(name, description, attributeMap.get("Health"),
						attributeMap.get("Boredom"), attributeMap.get("Happiness"), attributeMap.get("OilLevel"));
			} else if (minorPetType.equalsIgnoreCase("cat")) {
				factoryBuiltPet = new RoboticCat(name, description, attributeMap.get("Health"),
						attributeMap.get("Boredom"), attributeMap.get("Happiness"), attributeMap.get("OilLevel"));
			}
		}

		return factoryBuiltPet;
	}

	private static void doAdoptaPet(VirtualPetShelter vps) {
		String petName = getPetNameByUserChoice();
		System.out.println("\nOops. We accidentally incenerated " + petName + "!\n");
		vps.adopt(petName);
	}

	private static String getPetNameByUserChoice() {
		String petName = "";
		while (petName.isEmpty()) {
			Menu pcMenu = buildPetChoiceMenu();
			System.out.println(pcMenu.genMenuString(2, "Which Pet would you like?"));
			petName = pcMenu.getSplitKeyMenuChoice(input);
		}

		return petName;
	}

	private static void doPlayWithaPet(VirtualPetShelter vps) {
		String petName = getPetNameByUserChoice();
		System.out.println("\nYou have a ball playing with " + petName + "!\n");
		vps.getPetByName(petName).playWith();
	}

	private static void doWaterPets(VirtualPetShelter vps) {
		System.out.println("\nYou turn the hose on full and really soak those poor carbon based lifeforms.\n");
		vps.waterAllPets();
	}

	private static void doOilPets(VirtualPetShelter vps) {
		System.out.println("\nEat your heart out VO5. This oil treatment is crunchy pets only.\n");
		vps.OilAllRobots();
	}

	public static String createPetStatusReportString(VirtualPetShelter vps) {
		StringBuffer output = new StringBuffer();
		Formatter formatter = new Formatter(output);

		formatter.format("%31s%n", "Pet Status Report");
		formatter.format("%9s | %6s | %7s | %5s | %6s | %6s | %4s | %4s | %4s%n", "Name", "Health", "Boredom", "Happy",
				"Hunger", "Thirst", "Poop", "Oil", "Clean");
		formatter.format("%9s | %6s | %7s | %5s | %6s | %6s | %4s | %4s | %4s%n", "---------", "------", "-------",
				"-----", "------", "------", "----", "----", "----");
		vps.getAllPets().forEach(vp -> {
			PetHolder petHolder = vps.findPetHolderByPetName(vp.getName()).get().getKey(); // should have made a get
																							// PetHolder....
			if (vp instanceof BagOfMostlyWater) {
				BagOfMostlyWater pet = (BagOfMostlyWater) vp;
				formatter.format("%9s | %6s | %7s | %5s | %6s | %6s | %4s | %4s | %4s%n", pet.getName(),
						pet.getHealth(), pet.getBoredom(), pet.getHappiness(), pet.getHunger(), pet.getThirst(),
						pet.getPoopiness(), "N/A", petHolder.getCleanliness());
			} else if (vp instanceof MechanizedEntity) {
				MechanizedEntity pet = (MechanizedEntity) vp;

				formatter.format("%9s | %6s | %7s | %5s | %6s | %6s | %4s | %4s | %4s%n", pet.getName(),
						pet.getHealth(), pet.getBoredom(), pet.getHappiness(), "N/A", "N/A", "N/A", pet.getOilLevel(),
						petHolder.getCleanliness());
			}
		});

		formatter.close();
		return output.toString();
	}

	public static String createBanner(VirtualPetShelter vps) {
		return "Welcome to " + vps + "!\n";
	}

	private static VirtualPetShelter VirtualPetShelterFactory() {
		return new VirtualPetShelter("Arthurs Wholistic Pet Shelter and Sasauge Factory") {
			{
				intake(new OrganicDog("Spot", "One eyed mutt"));
				intake(new OrganicCat("Muffy", "100 pound Terrier"));
				intake(new RoboticCat("Rex", "Feisty Gecko"));
				intake(new RoboticDog("Capn Jack", "Bald Parrot"));
				// addPet(new VirtualPet("Snappy", "Introverted Turtle"));
			}
		};
	}

	private static void goodBye() {
		// TODO println must die! (somehow?)
		System.out.println("\nGoodBye!\n");
		input.close();
		System.exit(0);
	}

}
