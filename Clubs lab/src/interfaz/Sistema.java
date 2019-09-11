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
		Menu();

	}




	public void Menu() {
		System.out.println(showWelcome());
		System.out.println(showMenu());

		int option = 0 ;
		while(option != 4) {
			try {
				option = i.nextInt();
				Cases(option);



			}catch(InputMismatchException e) {
				e.getMessage();
			}

		}
	}

	public String showMenu() {
		String message = "";
		message += "What do you want to do? \n";
		message += "1. Create a Club \n";
		message += "2. Create an Owner \n";
		message += "3. Create a Pet \n";
		message += "4. Exit \n";
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
			bye();
			break;
		default: errorNumber();

		}
	}

	public void CreateClub() {
		showCreateClubMessage();
		System.out.println("The club needs an id, be careful you can't enter an id that has already taken");
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
		Menu();
	}

	public void CreateOwner() {
		
		if(clubs.size() == 0) {
			System.out.println("You must create a club first");
			showMenu();
			Menu();
		}else {
		showCreateOwnerMessage();
			System.out.println("Select the club in wich the owner is going to be");
			ShowClubList();
			int c = ClubExist();

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

			System.out.println("\n You've been added an owner successfully");
		}	
	}


	public int ClubExist() {
		int c = i.nextInt();
		if(clubs.get(c-1) == null) {
			System.out.println("Upss... that club doesn't exist");
			ClubExist();
		}
		return c-1;


	}
	public void CreatePet() {

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
