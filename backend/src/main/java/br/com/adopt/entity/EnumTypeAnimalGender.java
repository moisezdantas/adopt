package br.com.adopt.entity;

public enum EnumTypeAnimalGender {

	FEMALE(0), MALE(1);
	
	private Integer type;
	
	EnumTypeAnimalGender(Integer type){
		this.type = type;
	}
	
	public Integer getType() {
		return type;
	}
}
