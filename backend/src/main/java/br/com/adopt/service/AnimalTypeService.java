package br.com.adopt.service;

import br.com.adopt.dto.AnimalTypeDTO;
import br.com.adopt.entity.AnimalType;
import br.com.adopt.repository.AnimalTypeRepository;
import br.com.adopt.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalTypeService {

    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    /**
     * Method for return list type aninal
     * @return lista AnimalTypeDTO
     */
    @Transactional(readOnly = true)
    public ArrayList<AnimalTypeDTO> findAll(){
        List<AnimalType> list = animalTypeRepository.findAll();
        ArrayList<AnimalTypeDTO> animalTypeDTOS = new ArrayList<>();
        list.forEach(x -> animalTypeDTOS.add(new AnimalTypeDTO(x)));
        return animalTypeDTOS;
    }

    /**
     * Method for find type animal by id
     * @param id
     * @return AnimalTypeDTO
     */
    @Transactional(readOnly = true)
    public AnimalTypeDTO findByAnimaTypeId(Long id){
        AnimalType animalType = findById(id);
        return new AnimalTypeDTO(animalType);
    }

    /**
     * Method for find type animal by id
     * @param id
     * @return AnimalType
     */
    @Transactional(readOnly = true)
    public AnimalType findById(Long id){
        Optional<AnimalType> animalTypeSource = animalTypeRepository.findById(id);
        AnimalType animalType = animalTypeSource.orElseThrow(() -> new ResourceNotFoundException("Entity not found:" + id));
        return animalType;
    }

}
