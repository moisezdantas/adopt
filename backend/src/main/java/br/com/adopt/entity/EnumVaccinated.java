package br.com.adopt.entity;

public enum EnumVaccinated {

	YES(0), NO(1);
	
	private Integer type;
	
	EnumVaccinated(Integer type){
		this.type = type;
	}
	
	public Integer getType() {
		return type;
	}
}
