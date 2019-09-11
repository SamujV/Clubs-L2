package model;

import java.util.ArrayList;

public class Club  {

	private ArrayList<Owner> owners = new ArrayList<Owner>();
	private int id; 
	private String name; 
	private String creationDate;
	private String petType;

	public Club(int idd, String nam, String date, String pet) {
		id = idd;
		name = nam;
		creationDate = date;
		petType = pet;
	}
	
	
	public void addOwner(int idd, String name, String birth, String pet) {
		
		Owner owner = new Owner(id, name, birth, pet);
		owners.add(owner);
	}
	
	
	public void sortById() {

	}
	public void sortByName() {

	}public void sortByCreationDay() {

	}public void sortByPetType() {

	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
