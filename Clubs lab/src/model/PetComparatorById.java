package model;

import java.util.Comparator;

public class PetComparatorById implements Comparator<Pet>{

	@Override
	public int compare(Pet p1, Pet p2) {
		int i = 0;
		if(p1.getId() > p2.getId()) {
			i = 1;
		}else if(p1.getId() < p2.getId()) {
			i = -1;
		}
		
		return i;
	}
	

}
