package br.com.adopt.entity;

public enum EnumTypeAnimalGender {

	FEMALE(1), MALE(2);
	
	private Integer type;
	
	EnumTypeAnimalGender(Integer type){
		this.type = type;
	}
	
	public Integer getType() {
		return type;
	}
}
