package br.com.adopt.service;

import br.com.adopt.dto.AdoptDTO;
import br.com.adopt.entity.Adopter;
import br.com.adopt.entity.Animal;
import br.com.adopt.entity.Person;
import br.com.adopt.repository.AdoptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AdoptService {

    @Autowired
    private AdoptRepository aoAdoptRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private AnimalService animalService;

    public void adopt(AdoptDTO dto) {
        Person person = personService.findPersonByUserId(dto.getUserId());
        Animal animal = animalService.findAnimalById(dto.getAnimalId());

        Adopter adopter = new Adopter();
        adopter.setAnimal(animal);
        adopter.setPerson(person);
        adopter.setCreatedAt(Calendar.getInstance());

    }

}
