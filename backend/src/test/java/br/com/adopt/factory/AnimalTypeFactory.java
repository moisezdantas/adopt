package br.com.adopt.factory;

import br.com.adopt.dto.AnimalTypeDTO;
import br.com.adopt.entity.AnimalType;

public class AnimalTypeFactory {

    public static AnimalType createBasic(Long id, String description){
        return new AnimalType(id, description);
    }

    public static AnimalTypeDTO createAnimalTypeDTO(Long id, String description){
        return new AnimalTypeDTO(id, description);
    }

    public static AnimalType createBasicAnimalType(){
        return new AnimalType(1L, "Basic");
    }
}
