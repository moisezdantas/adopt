package br.com.adopt.factory;

import br.com.adopt.dto.BreedDTO;
import br.com.adopt.entity.Breed;

public class BreedFactory {

    public static BreedDTO createBasicDTO(Long id) {
        return new BreedDTO(id, "Gato Louco", "Brasil", "Medio", "13 anos",
                "Leal inteligente", AnimalTypeFactory.createAnimalTypeDTO(1l, "GATO"));
    }

    public static Breed createBasic(Long id) {
        return new Breed(id, "Gato Louco", "Brasil", "Medio", "13 anos",
                "Leal inteligente", AnimalTypeFactory.createBasic(1l, "GATO"));
    }

}
