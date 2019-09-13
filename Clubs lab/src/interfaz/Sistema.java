package interfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Club;


public class Sistema {

	Scanner i = new Scanner(System.in);
	Scanner s = new Scanner(System.in);

	public static final String CLUBS_DATA = "data/clubs.txt";
	public static final String OWNERS_DATA = "data/owners.txt";
	public static final String PETS_DATA = "data/pets.txt";
	private ArrayList<Club> clubs = new ArrayList<Club>();

	public static void main(String[] args) throws IOException {

		Sistema m = new Sistema();

	}
	public Sistema() throws IOException {
		System.out.println(showWelcome());
		Menu();

	}




	public void Menu() throws IOException {

		int option = 0;
		while(option != 5) {
			try {
				System.out.println(showMenu());
				option = i.nextInt();
				Cases(option);

			}catch(InputMismatchException e) {
				System.out.println("Please select a valid option...");
				i.nextLine();
			}

		}
	}

	public String showMenu() {
		String message = "";
		message += "What do you want to do? \n";
		message += "1. Create a Club \n";
		message += "2. Create an Owner \n";
		message += "3. Create a Pet \n";
		message += "4. Use test data \n";
		message += "5. Exit \n";
		return message;

	}

	public void Cases(int option) throws IOException {
		switch (option){
		case 1:
			CreateClub();
			break;
		case 2:
			CreateOwner();
			break;
		case 3:
			CreatePet();
			break;
		case 4:
			loadTestData();
			break;
		case 5:
			bye();			
			break;
		default: errorNumber();

		}
	}


	public void CreateClub() {
		showCreateClubMessage();
		System.out.println("The club needs an id, be careful you can't enter an id that is already taken");
		String id = s.nextLine();
		boolean isId = clubIdIsUnique(id);
		while(!isId) {
			System.out.println("Please insert another id...");
			id = s.nextLine();
			isId = clubIdIsUnique(id);
		}
		System.out.println("Enter name ");
		String name = s.nextLine();
		System.out.println("Enter the creation date dd/mm/aa");
		s.nextLine();
		String date = i.nextLine();
		System.out.println("Enter the pet type");
		System.out.println("" + petType());
		String type = s.nextLine();

		Club club = new Club(id, name, date, type);
		clubs.add(club);

		System.out.println("\n" + clubAdedCorrectly());
	}

	private boolean clubIdIsUnique(String id) { 
		boolean esta = true;
		if(clubs.size() > 0) {

			for (int i = 0; i < clubs.size() && esta == true; i++) {

				if(clubs.get(i).getId().equals(id)) {
					System.out.println("You can't use this id, it is already taken");
					esta = false;
				}				
			}
		}
		return esta;

	}
	public void CreateOwner() throws IOException {
		showCreateOwnerMessage();
		int c = -1;
		if(clubs.size() == 0) {
			System.out.println("\nYou must create a club first \n");
			showMenu();
			Menu();
		}else {
			try {
				System.out.println("Select the club in wich the owner is going to be");
				ShowClubList();
				c = ClubExist();

				System.out.println("An owner needs an id, be careful you can't enter an id that has already taken");
				String id = s.nextLine();
				boolean isId = ownerIdIsUnique(id, c);
				while(!isId) {
					System.out.println("\nThis id is already taken");
					System.out.println("Please insert another id...");
					id = s.nextLine();
					isId = ownerIdIsUnique(id, c);
				}

				System.out.println("Enter name ");
				String name = s.nextLine();
				i.nextLine();
				System.out.println("Enter the birthday dd/mm/aa");
				String date = i.nextLine();
				System.out.println("Enter the favorite pet type");
				System.out.println("" + petType());
				String type = s.nextLine();
				clubs.get(c).addOwner(id, name, date, type);
				System.out.println("\n You've been added an owner successfully");
			}catch(IndexOutOfBoundsException e) {
				System.out.println("There is no such a club");
			}catch(InputMismatchException e) {
				System.out.println("Please insert the correct format ");

			}

		}


	}

	private boolean ownerIdIsUnique(String idd, int club) {
		boolean esta = true;
		if(clubs.get(club).getOwners().size() > 0) {

			for (int i = 0; i < clubs.get(club).getOwners().size() && esta == true; i++) {
				String idOwner = clubs.get(club).getOwners().get(i).getId(); 
				if(idOwner.equals(idd) == true){
					System.out.println("You can use this id, it is already taken");
					esta = false;
				}				
			}
		}
		return esta;

	}

	public void CreatePet() throws IOException {
		showCreatePetMessage();
		int c = 0;
		int o = 0;
		if(clubs.size() == 0 || clubs.get(0).getOwners().size()==0) {
			System.out.println("\nYou must create a club and an owner first\n");
			showMenu();
			Menu();
		}else {
			try {
				System.out.println("Select owner's club");
				ShowClubList();
				c = ClubExist();
				System.out.println("OWNERS.\n");
				ShowOwnerList(clubs.get(c));

				System.out.println("Select your owner");
				o = i.nextInt();
				--o;

				System.out.println("Enter name ");
				String name = s.nextLine();
				boolean petNExist = petNameExist(c, o, name);
				while(!petNExist) {
					System.out.println("\nThis name is already taken");
					System.out.println("Please insert another name...");
					name = s.nextLine();
					petNExist = petNameExist(c, o, name);
				}
				System.out.println("Insert your pet's id.");
				i.nextLine();
				s.nextLine();
				int id = i.nextInt();

				boolean idexist = petIdIsUnique(id, c, o);
				while(!idexist) {
					System.out.println("\nThis id is already taken");
					System.out.println("Please insert another id...");
					id = i.nextInt();
					idexist = petIdIsUnique(id, c, o);
				}



				i.nextLine();
				System.out.println("Enter the birthday dd/mm/aa");
				String date = i.nextLine();
				System.out.println("Enter genre");
				String genre = s.nextLine();
				System.out.println("Enter type");
				petType();
				String type = s.nextLine();
				clubs.get(c).getOwners().get(o).addPet(id, name, date, genre, type);
				System.out.println("\n You've been added a pet successfully");

			}catch(InputMismatchException e) {
				System.out.println("Please insert the correct format ");

			}catch(IndexOutOfBoundsException e) {
				System.out.println("There is no pet with that id");
			}

		}



	}

	public boolean petNameExist(int club,int owner, String name) {

		boolean esta = true;
		if(clubs.get(club).getOwners().get(owner).getPets().size() > 0 && esta) {

			for (int i = 0; i < clubs.get(club).getOwners().get(owner).getPets().size() && esta; i++) {

				if(clubs.get(club).getOwners().get(owner).getPets().get(i).getName().equalsIgnoreCase(name)) {
					esta = false;
				}

			}
		}
		return esta;



	}

	private boolean petIdIsUnique(int id, int club, int owner) {

		boolean esta = true;

		if(clubs.get(club).getOwners().get(owner).getPets().size() > 0) {

			for (int i = 0; i < clubs.get(club).getOwners().get(owner).getPets().size() && esta ; i++) {

				if(clubs.get(i).getOwners().get(owner).getPets().get(i).getId() == id) {
					esta = false;
				}				
			}
		}
		return esta;

	}



	public int ClubExist() {
		int c = i.nextInt();
		--c;
		if(clubs.get(c) == null) {
			System.out.println("Upss... that club doesn't exist\n");
			System.out.println("try one more time...");
			ClubExist();

		}
		return c;


	}
	
	public void ShowClubList() {

		try {
			for (int i = 1; i <= clubs.size(); i++) {
				System.out.println(i +". "+ clubs.get(i-1).getName());
			}
		}catch(NullPointerException e) {
			e.getMessage();
		}
	}

	public void ShowOwnerList(Club c) {

		try {
			for (int i = 1; i <= c.getOwners().size(); i++) {
				System.out.println(i +". "+ c.getOwners().get(i-1).getName());
			}
		}catch(NullPointerException e) {
			e.getMessage();
		}
	}




	private void loadTestData() throws IOException {

		loadClubsFile(CLUBS_DATA);


	}
	public void loadClubsFile(String path) throws IOException {
		
		
		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		String line = br.readLine();
		while(line != null) {
			String[] parts = line.split(",");

			String id = parts[0];
			String name = parts[1];
			String date = parts[2];
			String pet = parts[3];

			Club c = new Club(id, name, date, pet);
			loadOwnersFile(c, OWNERS_DATA);
			clubs.add(c);
		
			line = br.readLine();
		}

		fr.close();

		br.close();
	}

	public void loadOwnersFile(Club c, String path) throws IOException {
		ArrayList<String[]> p = loadPetsFile(PETS_DATA);
		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
	
		while(line != null) {
			String[] parts = line.split(",");
			
			String id = parts[0];
			String name = parts[1];
			String date = parts[2];
			String pet = parts[3];

			 c.addOwner(id, name, date, pet);
			 int amountPets = (int) Math.random()*100000;
			 for (int i = 0; i < amountPets ; i++) {
				 	String[] partsP = p.get(i);
					int idP = Integer.parseInt(partsP[0]);
					String nameP = partsP[1];
					String birthP = partsP[2];
					String genreP = partsP[3];
					String typeP = partsP[4];

				
				 c.getOwners().get(c.getOwners().size()-1).addPet(idP, nameP, birthP, genreP, typeP);
				 
			}

			line = br.readLine();
		}

		fr.close();

		br.close();
	}

	public ArrayList<String[]> loadPetsFile(String path) throws IOException {
		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		ArrayList<String[]> pets = new ArrayList<String[]>();
		String line = br.readLine();
		while(line != null) {
			String[] parts = line.split(",");
			pets.add(parts);


			line = br.readLine();
		}

		fr.close();

		br.close();
		return pets;
	}




	public String clubAdedCorrectly() {
		String message = "";
		message += "=========================================================\n";
		message += "================= CLUB ADED SUCCESFULY ==================\n";
		message += "=========================================================\n";
		return message;


	}

	public String showWelcome() {
		String message = "";
		message += "=========================================================\n";
		message += "================= Welcome to the SYSTEM==================\n";
		message += "=========================================================\n";
		return message;
	}
	public String petType() {
		String message = "";
		message += "Dog \n";
		message += "Cat \n";
		message += "Bird \n";
		message += "Reptile \n";
		message += "Fish \n";
		message += "Invertebrate";
		return message;
	}
	public void showCreateClubMessage() {
		String message = "";
		message += "=========================================================\n";
		message += "====================   CREATE A CLUB   ==================\n";
		message += "=========================================================\n";
		System.out.println("" + message);
	}
	public void showCreateOwnerMessage() {
		String message = "";
		message += "=========================================================\n";
		message += "====================   CREATE AN OWNER   =================\n";
		message += "=========================================================\n";
		System.out.println("" + message);
	}
	public void showCreatePetMessage() {
		String message = "";
		message += "=========================================================\n";
		message += "====================   CREATE A PET   ==================\n";
		message += "=========================================================\n";
		System.out.println("" + message);
	}


	public void bye() {
		System.out.println("Thanks for using the program");
	}
	public void errorNumber() {

		System.out.println(" Ups... this is not an option");
	}



}
