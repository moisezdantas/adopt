package br.com.adopt.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name = "tb_breed")
public class Breed implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String origin;
	private String height;
	private String lifeExpectancy;
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "animal_type_id")
	private AnimalType animalType;

	@Column(name = "created_at")
	private Calendar createdAt;
	
	public Breed() {}

	public Breed(Long id, String name, String origin, String height, String lifeExpectancy, String description, AnimalType animalType) {
		this.id = id;
		this.name = name;
		this.origin = origin;
		this.height = height;
		this.lifeExpectancy = lifeExpectancy;
		this.description = description;
		this.animalType = animalType;
	}

	@PrePersist
	public void prePersist() {
		createdAt = Calendar.getInstance();
	}

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}

	public String getOrigin() {return origin;}

	public void setOrigin(String origin) {this.origin = origin;	}

	public String getHeight() {return height;}

	public void setHeight(String height) {this.height = height;}

	public String getLifeExpectancy() {return lifeExpectancy;}

	public void setLifeExpectancy(String lifeExpectancy) {this.lifeExpectancy = lifeExpectancy;}

	public String getDescription() {return description;}

	public void setDescription(String description) {this.description = description;}

	public AnimalType getAnimalType() {return animalType;}

	public void setAnimalType(AnimalType animalType) {this.animalType = animalType;}

	public Calendar getCreatedAt() {return createdAt;}

	public void setCreatedAt(Calendar createdAt) {this.createdAt = createdAt;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Breed other = (Breed) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
