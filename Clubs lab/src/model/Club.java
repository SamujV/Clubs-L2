package model;

import java.util.ArrayList;
 
public class Club implements Comparable{

	private ArrayList<Owner> owners = new ArrayList<Owner>();
	private String id; 
	private String name; 
	private String creationDate;
	private String petType;

	public Club(String idd, String nam, String date, String pet) {
		id = idd;
		name = nam;
		creationDate = date;
		petType = pet;
	}
	
	
	public void addOwner(String idd, String name, String birth, String pet) {
		
		Owner owner = new Owner(idd, name, birth, pet);
		owners.add(owner);
	}
	
	
	public void sortById() {

	}
	public void sortByName() {

	}public void sortByCreationDay() {

	}public void sortByPetType() {

	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Owner> getOwners(){
		return owners;
	}
	
	public void setOwners(ArrayList<Owner> o) {
		owners = o;
	}


	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
