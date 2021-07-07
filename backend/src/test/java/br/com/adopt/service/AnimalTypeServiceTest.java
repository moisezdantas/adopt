package br.com.adopt.service;

import br.com.adopt.dto.AnimalTypeDTO;
import br.com.adopt.entity.AnimalType;
import br.com.adopt.factory.AnimalTypeFactory;
import br.com.adopt.repository.AnimalTypeRepository;
import br.com.adopt.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AnimalTypeServiceTest {

    @InjectMocks
    private AnimalTypeService animalTypeService;

    @Mock
    private AnimalTypeRepository animalTypeRepository;

    private AnimalType animalType;
    private AnimalType animalType2;
    private AnimalTypeDTO animalTypeDTO;
    private Long animalTypeNotFound;
    private Long animalTypeFound;
    private List<AnimalType> listAnimalType;

    @BeforeEach
    public void setup() throws Exception{
        animalType = AnimalTypeFactory.createBasic(1L, "CAT");
        animalType2 = AnimalTypeFactory.createBasic(2L, "DOG");
        animalTypeNotFound = 1000L;
        animalTypeFound = 1l;
        listAnimalType = List.of(animalType, animalType2);

    }

    @Test
    public void findAllShouldReturnAnimalType() {
        when(animalTypeRepository.findAll()).thenReturn(listAnimalType);
        animalTypeService.findAll();
        verify(animalTypeRepository, times(1)).findAll();
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundException(){
        assertThrows(ResourceNotFoundException.class, () -> {
            animalTypeService.findByAnimaTypeId(animalTypeNotFound);
        });
        verify(animalTypeRepository, times(1)).findById(animalTypeNotFound);
    }

    @Test
    public void findByIdShouldReturnAnimalType(){
        when(animalTypeRepository.findById(animalTypeFound)).thenReturn(Optional.of(animalType));
        animalTypeService.findByAnimaTypeId(animalTypeFound);
        verify(animalTypeRepository, times(1)).findById(animalTypeFound);
    }

}
