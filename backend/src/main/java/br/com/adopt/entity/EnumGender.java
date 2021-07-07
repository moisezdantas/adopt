package br.com.adopt.entity;

public enum EnumGender {

	MAN(1), WOMEN(2);
	
	private Integer type;
	
	EnumGender(Integer type){
		this.type = type;
	}
	
	public Integer getType() {
		return type;
	}
}
