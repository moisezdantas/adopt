package br.com.adopt.factory;

import br.com.adopt.dto.AnimalDTO;
import br.com.adopt.entity.Animal;
import br.com.adopt.entity.EnumTypeAnimalGender;
import br.com.adopt.entity.EnumVaccinated;

public class AnimalFactory {

    public static Animal createAnimalBasic(Long id) {
        Animal animal = new Animal();
        animal.setId(id);
        animal.setAnimalType(AnimalTypeFactory.createBasicAnimalType());
        animal.setRga("1232s");
        animal.setBirthDate("18/12/2010");
        animal.setDeficiency("12 e 18 anos");
        animal.setIsVaccinated(EnumVaccinated.YES);
        animal.setTypeAnimalGender(EnumTypeAnimalGender.MALE);
        animal.setIsCastrated(false);
        animal.setYear(15);
        animal.setNote("Animal novo");
        animal.setBreed(BreedFactory.createBasic(1L));
        animal.setAddress(AddressFactory.createBasic(1L));
        return animal;
    }

    public static AnimalDTO createAnimalBasicDTO(Long id) {
        Animal animal = createAnimalBasic(id);
        AnimalDTO dto = new AnimalDTO(animal);
        return dto;
    }
}
