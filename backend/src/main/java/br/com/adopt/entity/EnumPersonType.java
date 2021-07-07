package br.com.adopt.entity;

public enum EnumPersonType {

	PHYSICAL(0), JURIDICAL(1);
	
	private Integer type;
	
	EnumPersonType(Integer type){
		this.type = type;
	}
	
	public Integer getType() {
		return type;
	}
}
