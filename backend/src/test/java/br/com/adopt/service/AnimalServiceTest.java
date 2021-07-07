package br.com.adopt.service;

import br.com.adopt.dto.AnimalDTO;
import br.com.adopt.entity.Animal;
import br.com.adopt.entity.User;
import br.com.adopt.factory.AnimalFactory;
import br.com.adopt.factory.RoleFactory;
import br.com.adopt.factory.UserFactory;
import br.com.adopt.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class AnimalServiceTest {

    @InjectMocks
    private AnimalService animalService;

    @Autowired
    private AnimalRepository animalRepository;

    private Animal animal;
    private Animal animal2;
    private AnimalDTO animalDTO;

    private Long animalNotFound;
    private Long animalFound;

    private PageRequest request;
    private Page<Animal> pages;

    @BeforeEach
    public void setup() throws Exception{
        animal = AnimalFactory.createAnimalBasic(1L);
        animal2 = AnimalFactory.createAnimalBasic(2L);
        animalDTO = AnimalFactory.createAnimalBasicDTO(null );
        animalNotFound = 1000L;
        animalFound = 1l;
        request = PageRequest.of(0, 12, Sort.Direction.ASC, "name");
        pages = new PageImpl<>(List.of(animal, animal2));
    }



}
