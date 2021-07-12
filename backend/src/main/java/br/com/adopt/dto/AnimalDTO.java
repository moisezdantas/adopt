package br.com.adopt.dto;

import br.com.adopt.entity.Animal;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class AnimalDTO implements Serializable {

    private Long id;
    private String name;
    private String rga; // Registro geral do animal
    private String birthDate;
    private String deficiency;
    private Integer typeVaccinated;
    private Integer typeAnimalGender;
    private Boolean isCastrated; // castrado
    private Integer year;
    private String note;
    private BreedDTO breed;
    private AddressDTO address;
    private String imageUrl;

    public AnimalDTO() {}

    public AnimalDTO(Long id, String name, String rga, String birthDate, String deficiency, Integer typeVaccinated, Integer typeAnimalGender, Boolean castrated, Integer year, String note, BreedDTO breed, AddressDTO address, String imageUrl) {
        this.id = id;
        this.name = name;
        this.rga = rga;
        this.birthDate = birthDate;
        this.deficiency = deficiency;
        this.typeVaccinated = typeVaccinated;
        this.typeAnimalGender = typeAnimalGender;
        this.isCastrated = castrated;
        this.year = year;
        this.note = note;
        this.breed = breed;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public AnimalDTO(Animal animal) {
        this.id = animal.getId();
        this.name = animal.getName();
        this.rga = animal.getRga();
        this.birthDate = animal.getBirthDate();
        this.deficiency = animal.getDeficiency();
        this.typeVaccinated =  animal.getIsVaccinated() != null ? animal.getIsVaccinated().ordinal(): null;
        this.typeAnimalGender = animal.getTypeAnimalGender().ordinal();
        this.isCastrated = animal.isCastrated();
        this.year = animal.getYear();
        this.note = animal.getNote();
        this.breed = new BreedDTO(animal.getBreed());
        this.address = animal.getAddress() != null ? new AddressDTO(animal.getAddress()) : null;
        this.imageUrl = animal.getImageUrl();;
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

    public String getRga() {
        return rga;
    }

    public void setRga(String rga) {
        this.rga = rga;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDeficiency() {
        return deficiency;
    }

    public void setDeficiency(String deficiency) {
        this.deficiency = deficiency;
    }

    public Integer getTypeVaccinated() {
        return typeVaccinated;
    }

    public void setTypeVaccinated(Integer typeVaccinated) {
        this.typeVaccinated = typeVaccinated;
    }

    public Integer getTypeAnimalGender() {
        return typeAnimalGender;
    }

    public void setTypeAnimalGender(Integer typeAnimalGender) {
        this.typeAnimalGender = typeAnimalGender;
    }

    public Boolean isCastrated() {
        return isCastrated;
    }

    public void setCastrated(Boolean castrated) {
        isCastrated = castrated;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BreedDTO getBreed() {
        return breed;
    }

    public void setBreed(BreedDTO breed) {
        this.breed = breed;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
