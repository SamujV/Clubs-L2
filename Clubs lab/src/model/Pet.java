package model;

import java.io.Serializable;

public class Pet implements Serializable{
	
	/**
	 *  
	 */
	private static final long serialVersionUID = -7548823462809095505L;
	
	private int id;
	private String name;
	private String birth;
	private String genre; 
	private String type;
	
	public Pet(int idd, String nam, String birthh, String genr, String typ) {
		id = idd;
		name = nam;
		birth = birthh;
		genre = genr;
		type = typ;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	
	
	
	
}
