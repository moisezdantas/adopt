package br.com.adopt.dto;

import br.com.adopt.entity.AnimalType;

public class AnimalTypeDTO {

    private Long id;
    private String description;

    public AnimalTypeDTO() {}

    public AnimalTypeDTO(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public AnimalTypeDTO(AnimalType type) {
        this.id = type.getId();
        this.description = type.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
