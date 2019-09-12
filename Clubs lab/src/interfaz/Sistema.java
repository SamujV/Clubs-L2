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
	private ArrayList<Club> clubs = new ArrayList<Club>();

	public static void main(String[] args) throws IOException {

		Sistema m = new Sistema();

	}
	public Sistema() throws IOException {
		System.out.println(showWelcome());
		Menu();

	}




	public void Menu() {

		int option = 0;
		while(option != 5) {
			try {
				System.out.println(showMenu());
				option = i.nextInt();
				Cases(option);

			}catch(InputMismatchException e) {
				System.out.println("Por favor digite una opcion valida");
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

	public void Cases(int option) {
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
		int id = i.nextInt();
		System.out.println("Enter name ");
		String name = s.nextLine();
		i.nextLine();
		System.out.println("Enter the creation date dd/mm/aa");
		String date = i.nextLine();
		System.out.println("Enter the pet type");
		System.out.println("" + petType());
		String type = s.nextLine();

		Club club = new Club(id, name, date, type);
		clubs.add(club);

		System.out.println("\n You've been added a club successfully");
	}

	public void CreateOwner() {
		int c = -1;
		if(clubs.size() == 0) {
			System.out.println("You must create a club first");
			showMenu();
			Menu();
		}else {
			try {
				showCreateOwnerMessage();
				System.out.println("Select the club in wich the owner is going to be");
				ShowClubList();
				c = ClubExist();

				System.out.println("An owner needs an id, be careful you can't enter an id that has already taken");
				int id = i.nextInt();
				System.out.println("Enter name ");
				String name = s.nextLine();
				i.nextLine();
				System.out.println("Enter the birthday dd/mm/aa");
				String date = i.nextLine();
				System.out.println("Enter the favorite pet type");
				System.out.println("" + petType());
				String type = s.nextLine();
				clubs.get(c).addOwner(id, name, date, type);
			}catch(IndexOutOfBoundsException e) {
				System.out.println("There is no such a club");
				CreateOwner();
			}catch(InputMismatchException e) {
				System.out.println("Please insert the correct format ");

			}

		}

		System.out.println("\n You've been added an owner successfully");

	}



	public void CreatePet() {
		int c = -1;
		int o = -1;
		if(clubs.size() == 0 || clubs.get(0).getOwners().size()==0) {
			System.out.println("You must create a club and an owner first");
			showMenu();
			Menu();
		}else {
			try {
				showCreatePetMessage();
				System.out.println("Select the owner's club");
				ShowClubList();
				c = ClubExist();
				ShowOwnerList(clubs.get(c));

				System.out.println("Select your owner");
				o = i.nextInt();

				System.out.println("Insert your pet's id.");
				int id = i.nextInt();
				System.out.println("Enter name ");
				String name = s.nextLine();
				i.nextLine();
				System.out.println("Enter the birthday dd/mm/aa");
				String date = i.nextLine();
				System.out.println("Enter genre");
				String genre = s.nextLine();
				System.out.println("Enter type");
				petType();
				String type = s.nextLine();
				clubs.get(c).getOwners().get(o-1).addPet(id, name, date, genre, type);

			}catch(IndexOutOfBoundsException e) {
				System.out.println("There is no such a club");
				CreateOwner();
			}catch(InputMismatchException e) {
				System.out.println("Please insert the correct format ");

			}

		}

		System.out.println("\n You've been added a pet successfully");
		System.out.println("breve?  tamaño de pets " + clubs.get(c).getOwners().get(o-1).getPets().size());


	}




	public int ClubExist() {
		int c = i.nextInt();
		--c;
		if(clubs.get(c) == null) {
			System.out.println("Upss... that club doesn't exist");

		}
		return c;


	}
	private void loadTestData() {

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



	private ArrayList<String> readData(String path)throws IOException {
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		ArrayList<String> words = new ArrayList<String>();

		String line = br.readLine();
		while(line != null){
			words.add(line);
			line = br.readLine();
		}
		br.close();
		fr.close();

		return words;
	}

	public void loadStudentsFile(String path, String sep) throws IOException {
		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);

		String line = br.readLine();
		while(line != null) {
			String[] parts = line.split(sep);

			String code = parts[0];
			int semester = Integer.parseInt(parts[1]);
			double av = Double.parseDouble(parts[2]);
			boolean ce = Boolean.parseBoolean(parts[3]);

			Student s = new Student(code, semester, av, ce);
			students.add(s);

			line = br.readLine();
		}

		fr.close();

		br.close();
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
