package interfaz;

import java.util.ArrayList;
import model.Club;

public class Sistema {
	
	private ArrayList<Club> clubs = new ArrayList<Club>();


	public static void main(String[] args) {
		
		Sistema m = new Sistema();
		
	}
	public Sistema() {
		System.out.println(" "  + showWelcome());
	}


	public String showWelcome() {
		String message = "";

		message += "				Welcome to the SYSTEM 				\n";
		message += " What do you want to do? \n";
		message += " 1. Create a Club \n";
		message += " 2. Create an Owner \n";
		message += " 3. Create a pet  \n";
				



		return message;
	}


	public void manageClubs() {
		clubs = new ArrayList<Club>();
		
		
	}






}
