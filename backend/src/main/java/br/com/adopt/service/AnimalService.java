package br.com.adopt.service;

import br.com.adopt.dto.AnimalDTO;
import br.com.adopt.entity.Animal;
import br.com.adopt.entity.EnumTypeAnimalGender;
import br.com.adopt.repository.AnimalRepository;
import br.com.adopt.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private AnimalTypeService animalTypeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private BreedService breedService;

    /**
     * Method for return list animal
     *
     * @param pageRequest
     * @return Page<AnimalDTO>
     */
    @Transactional(readOnly = true)
    public Page<AnimalDTO> findAllPaged(PageRequest pageRequest) {
        Page<Animal> page = animalRepository.findAll(pageRequest);
        return page.map(x -> new AnimalDTO(x));
    }

    /**
     * Method for return list animal
     *
     * @param pageRequest
     * @return Page<AnimalDTO>
     */
    @Transactional(readOnly = true)
    public Page<AnimalDTO> findAllAnimalTypePaged(Long id, PageRequest pageRequest) {
        Page<Animal> page = null;
        if(id != 0L){
            page = animalRepository.findAllByAnimalType(id, pageRequest);
        }else {
            page = animalRepository.findAll(pageRequest);
        }

        return page.map(x -> new AnimalDTO(x));
    }

    /**
     * Method for return find by id
     *
     * @param id
     * @return AnimalDTO
     */
    @Transactional(readOnly = true)
    public AnimalDTO findById(Long id) {
        Optional<Animal> animalSource = animalRepository.findById(id);
        Animal animal = animalSource.orElseThrow(() -> new ResourceNotFoundException("Entity not found:" + id));
        return new AnimalDTO(animal);
    }

    /**
     * Method for return find by id
     *
     * @param id
     * @return AnimalDTO
     */
    @Transactional(readOnly = true)
    public Animal findAnimalById(Long id) {
        Optional<Animal> animalSource = animalRepository.findById(id);
        Animal animal = animalSource.orElseThrow(() -> new ResourceNotFoundException("Entity not found:" + id));
        return animal;
    }

    /**
     * Method for create animal
     *
     * @param dto
     * @return AnimalDTO
     */
    @Transactional(readOnly = true)
    public AnimalDTO create(AnimalDTO dto) {
        Animal animal = new Animal();
        copyDtoToEntity(dto, animal);
        return new AnimalDTO(animal);
    }

    /**
     * Method for copy dto to entity animal
     *
     * @param dto
     * @param animal
     */
    private void copyDtoToEntity(AnimalDTO dto, Animal animal) {
        animal.setYear(dto.getYear());
        animal.setIsCastrated(dto.isCastrated());
        animal.setAddress(addressService.createAddress(dto.getAddress()));
        animal.setBreed(breedService.findById(dto.getBreed().getId()));
        animal.setIsCastrated(dto.isCastrated());
        animal.setNote(dto.getNote());
        animal.setDeficiency(dto.getDeficiency());
        animal.setBirthDate(dto.getBirthDate());
        animal.setRga(dto.getRga());
        animal.setName(dto.getName());

        if (animal.getTypeAnimalGender() != null) {
            EnumTypeAnimalGender typeAnimalGender = animal.getTypeAnimalGender().equals(EnumTypeAnimalGender.MALE.ordinal()) ?
                    EnumTypeAnimalGender.MALE : EnumTypeAnimalGender.FEMALE;
            animal.setTypeAnimalGender(typeAnimalGender);
        }
    }

}
