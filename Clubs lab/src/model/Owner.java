package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Owner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Pet> pets = new ArrayList<Pet>();
	private String id;
	private String name; 
	private String birth;
	private String petFav;


	public Owner(String idd, String nam, String birthh, String pet) {
		id = idd;
		name = nam;
		birth = birthh;
		petFav = pet ;
		
	}
	
	public void addPet(int idd, String nam, String birthh, String genr, String typ) {
		Pet pet = new Pet(idd, nam, birthh, genr, typ);
		pets.add(pet);
		
	}
	public void sortById() {

	}
	public void sortByName() {

	}public void sortByLastName() {

	}public void sortByBirthDay() {

	}public void sortByFavPet() {

	}

	public String getName() {
		return name;
	}

	public ArrayList<Pet> getPets(){
		return pets;
	}
	
	
	public String getId() {
		return id;
	}
}
