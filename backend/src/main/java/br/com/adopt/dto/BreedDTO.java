package br.com.adopt.dto;

import br.com.adopt.entity.Breed;

import java.io.Serializable;

public class BreedDTO implements Serializable {

    private Long id;
    private String name;
    private String origin;
    private String height;
    private String lifeExpectancy;
    private String description;
    private AnimalTypeDTO animalTypeDTO;

    public BreedDTO() {}

    public BreedDTO(Long id, String name, String origin, String height, String lifeExpectancy, String description, AnimalTypeDTO animalTypeDTO) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.height = height;
        this.lifeExpectancy = lifeExpectancy;
        this.description = description;
        this.animalTypeDTO = animalTypeDTO;
    }

    public BreedDTO(Breed breed) {
        this.id = breed.getId();
        this.name = breed.getName();
        this.origin = breed.getOrigin();
        this.height = breed.getHeight();
        this.lifeExpectancy = breed.getLifeExpectancy();
        this.description = breed.getDescription();
        this.animalTypeDTO = new AnimalTypeDTO(breed.getAnimalType());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(String lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AnimalTypeDTO getAnimalTypeDTO() {
        return animalTypeDTO;
    }

    public void setAnimalTypeDTO(AnimalTypeDTO animalTypeDTO) {
        this.animalTypeDTO = animalTypeDTO;
    }
}
