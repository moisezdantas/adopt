package br.com.adopt.service;

import br.com.adopt.dto.PersonDTO;
import br.com.adopt.dto.UserDTO;
import br.com.adopt.entity.EnumStatus;
import br.com.adopt.entity.Person;
import br.com.adopt.factory.PersonFactory;
import br.com.adopt.factory.UserFactory;
import br.com.adopt.repository.PersonRepository;
import br.com.adopt.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private UserService userService;

    private Person person;
    private Person personSaved;
    private PersonDTO personDTO;

    private UserDTO userDTO;
    private Long personNotFound;
    private Long personFound;

    @BeforeEach
    public void setup() throws Exception{
        person = PersonFactory.createPersonBasic(null, EnumStatus.ACTIVE);
        personDTO = PersonFactory.createPersonBasicDTO(null);
        personSaved = PersonFactory.createPersonBasic(1L, EnumStatus.ACTIVE);;
        userDTO = UserFactory.createUserBasicDTO(1L);
    }

    @Test
    public void shouldReturnCreatePerson(){
        when(userService.createUser(any())).thenReturn(userDTO);
        when(personRepository.save(person)).thenReturn(personSaved);
        personService.createPerson(personDTO);
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundException(){
        assertThrows(ResourceNotFoundException.class, () -> {
            personService.findById(personNotFound);
        });
        verify(personRepository, times(1)).findById(personNotFound);
    }

    @Test
    public void findByIdShouldReturnPerson(){
        when(personRepository.findById(personFound)).thenReturn(Optional.of(personSaved));
        personService.findById(personFound);
        verify(personRepository, times(1)).findById(personFound);
    }

    @Test
    public void updateShouldThrowResourceNotFoundException(){
        assertThrows(ResourceNotFoundException.class,  () -> {
            personService.update(personNotFound, personDTO);
        });
        verify(personRepository, times(1)).findById(personNotFound);
    }

    @Test
    public void updateShouldReturnPerson(){
        when(personRepository.findById(personFound)).thenReturn(Optional.of(personSaved));
        when(userService.update(anyLong(), any())).thenReturn(userDTO);
        when(personRepository.save(personSaved)).thenReturn(personSaved);
        personService.update(personFound, personDTO);
        verify(personRepository, times(1)).findById(personFound);
        verify(personRepository, times(1)).save(personSaved);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundException(){
        assertThrows(ResourceNotFoundException.class,  () -> {
            personService.delete(personNotFound);
        });
        verify(personRepository, times(1)).findById(personNotFound);
    }

    @Test
    public void deleteShouldStatusInactiveSuccess(){
       when(personRepository.findById(personFound)).thenReturn(Optional.of(personSaved));
       personService.delete(personFound);
       verify(personRepository, times(1)).findById(personNotFound);
       verify(personRepository, times(1)).save(personSaved);
    }
}
