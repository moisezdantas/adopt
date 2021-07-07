package br.com.adopt.service;

import br.com.adopt.dto.AnimalTypeDTO;
import br.com.adopt.dto.BreedDTO;
import br.com.adopt.entity.AnimalType;
import br.com.adopt.entity.Breed;
import br.com.adopt.repository.BreedRepository;
import br.com.adopt.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BreedService {

    @Autowired
    private BreedRepository breedRepository;

    /**
     * Method for return list Breeds
     * @return ArrayList<BreedDTO>
     */
    @Transactional(readOnly = true)
    public ArrayList<BreedDTO> findAll() {
        List<Breed> list = breedRepository.findAll();
        ArrayList<BreedDTO> breedDTOS = new ArrayList<>();
        list.forEach(x -> breedDTOS.add(new BreedDTO(x)));
        return breedDTOS;
    }

    /**
     * Method for return list breeds for by animalType
     * @return ArrayList<BreedDTO>
     */
    @Transactional(readOnly = true)
    public ArrayList<BreedDTO> findAllByTypeAnimal(Long id) {
        List<Breed> list = breedRepository.findByAnimalType(id);
        ArrayList<BreedDTO> breedDTOS = new ArrayList<>();
        list.forEach(x -> breedDTOS.add(new BreedDTO(x)));
        return breedDTOS;
    }

    /**
     * Method for find type breed by id
     * @param id
     * @return breedDTO
     */
    @Transactional(readOnly = true)
    public BreedDTO findByBreedId(Long id){
        Breed breed = findById(id);
        return new BreedDTO(breed);
    }

    /**
     * Method for find type breed by id
     * @param id
     * @return breedDTO
     */
    @Transactional(readOnly = true)
    public Breed findById(Long id){
        Optional<Breed> breedSource = breedRepository.findById(id);
        Breed breed = breedSource.orElseThrow(() -> new ResourceNotFoundException("Entity not found:" + id));
        return breed;
    }

}
