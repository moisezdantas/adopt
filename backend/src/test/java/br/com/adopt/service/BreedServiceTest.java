package br.com.adopt.service;

import br.com.adopt.entity.Breed;
import br.com.adopt.factory.BreedFactory;
import br.com.adopt.repository.BreedRepository;
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
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
public class BreedServiceTest {

    @InjectMocks
    private BreedService service;

    @Mock
    private BreedRepository breedRepository;

    private Breed breed;
    private Breed breed2;
    private List<Breed> breedList;
    private Long breedFound;
    private Long breedNotFound;
    private Long animalTypeId;

    @BeforeEach
    public void setup() throws Exception{
        breed = BreedFactory.createBasic(1L);
        breed2 = BreedFactory.createBasic(2L);
        breedNotFound = 1000L;
        breedFound = 1l;
        animalTypeId = 1l;
        breedList = List.of(breed, breed2);
    }

    @Test
    public void findAllShouldReturnBreed() {
       when(breedRepository.findAll()).thenReturn(breedList);
       service.findAll();
       verify(breedRepository, times(1)).findAll();
    }


    @Test
    public void findAllAnimalTypeShouldReturnBreed() {
        when(breedRepository.findByAnimalType(animalTypeId)).thenReturn(breedList);
        service.findAllByTypeAnimal(animalTypeId);
        verify(breedRepository, times(1)).findByAnimalType(animalTypeId);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundException(){
     assertThrows(ResourceNotFoundException.class, () -> {
        service.findByBreedId(breedNotFound);
     });
       verify(breedRepository, times(1)).findById(breedNotFound);
    }

    @Test
    public void findByIdShouldReturnBreed(){
        when(breedRepository.findById(breedFound)).thenReturn(Optional.of(breed));
        service.findByBreedId(breedFound);
        verify(breedRepository, times(1)).findById(breedFound);
    }
}
