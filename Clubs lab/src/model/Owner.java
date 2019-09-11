package model;

import java.io.Serializable;

public class Owner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name; 
	private String birth;
	private String petFav;


	public Owner(int idd, String nam, String birthh, String pet) {
		id = idd;
		name = nam;
		birth = birthh;
		petFav = pet ;
	}
	public void sortById() {

	}
	public void sortByName() {

	}public void sortByLastName() {

	}public void sortByBirthDay() {

	}public void sortByFavPet() {

	}


}
