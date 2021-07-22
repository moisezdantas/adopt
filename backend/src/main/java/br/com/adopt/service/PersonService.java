package br.com.adopt.service;

import br.com.adopt.dto.PersonDTO;
import br.com.adopt.dto.UserDTO;
import br.com.adopt.entity.*;
import br.com.adopt.repository.AddressRepository;
import br.com.adopt.repository.PersonRepository;
import br.com.adopt.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    /**
     * Method for create person
     * @param personDTO
     * @return PersonDTO
     */
    @Transactional
    public PersonDTO createPerson(PersonDTO personDTO) {
        UserDTO userDTO = userService.createUser(personDTO.getUser());
        personDTO.getUser().setId(userDTO.getId());
        Person person = new Person();
        copyDtoToEntity(personDTO, person);
        person = personRepository.save(person);
        return new PersonDTO(person);
    }

    /**
     * Method for find person by id
     * @param id
     * @return PersonDTO
     */
    @Transactional(readOnly = true)
    public PersonDTO findById(Long id){
        Person person = findByPersonId(id);
        return new PersonDTO(person);
    }

    /**
     * Method to updated person by id
     * @param id
     * @param personDTO
     * @return PersonDTO
     */
    @Transactional
    public PersonDTO update(Long id, PersonDTO personDTO){
        Person personExist = id != null ? findByPersonId(id): findPersonByUserId(personDTO.getUser().getId());
        userService.update(personDTO.getUser().getId(), personDTO.getUser());
        copyDtoToEntity(personDTO, personExist);
        personExist = personRepository.save(personExist);
        return new PersonDTO(personExist);
    }


    /**
     * Method to updated user by id
     * @param personDTO
     * @return PersonDTO
     */
    @Transactional
    public PersonDTO updateByUser(PersonDTO personDTO){
        Person personExist = findPersonByUserId(personDTO.getUser().getId());
        copyDtoToEntity(personDTO, personExist);
        personExist = personRepository.save(personExist);
        return new PersonDTO(personExist);
    }


    /**
     * Method to delete user by id
     * @param id
     */
    @Transactional
    public void delete(Long id){
        Person personExist = findByPersonId(id);
        personExist.setStatus(EnumStatus.INACTIVE);
        personRepository.save(personExist);
    }

    /**
     * Method for find person by id
     * @param id
     * @return Person
     */
    private Person findByPersonId(Long id){
        Optional<Person> personFind = personRepository.findById(id);
        return personFind.orElseThrow(() -> new ResourceNotFoundException("Entity not found:" + id));
    }

    /**
     * Method for find person by id
     * @param id
     * @return Person
     */
    public Person findPersonByUserId(Long id){
        Optional<Person> personFind = personRepository.findPersonByUserId(id);
        return personFind.orElseThrow(() -> new ResourceNotFoundException("Entity not found:" + id));
    }

    /**
     * Method copy dto to entity
     * @param personDTO
     * @param person
     * @return Person
     */
    private Person copyDtoToEntity(PersonDTO personDTO, Person person){
        if(personDTO.getName() != null){
            person.setName(personDTO.getName());
        }

        person.setMobilePhone(personDTO.getMobilePhone());
        person.setCnpj(person.getCnpj());
        person.setRg(person.getRg());
        person.setCnpj(person.getCnpj());

        Set<Address> addressList = new HashSet<>();
        personDTO.getAddress().forEach(x -> addressList.add(new Address(x)));
        person.setUser(new User(personDTO.getUser().getId()));

        if(personDTO.getTypePerson() == EnumPersonType.JURIDICAL.getType()){
            person.setTypePerson(EnumPersonType.JURIDICAL);
        }

        if(personDTO.getTypePerson() == EnumPersonType.PHYSICAL.getType()){
            person.setTypePerson(EnumPersonType.PHYSICAL);
        }

        if(personDTO.getTypeGender() == EnumGender.MAN.getType()){
            person.setTypeGender(EnumGender.MAN);
        }

        if(personDTO.getTypeGender() == EnumGender.WOMEN.getType()){
            person.setTypeGender(EnumGender.WOMEN);
        }

        if(personDTO.getStatus() == null){
            person.setStatus(EnumStatus.ACTIVE);
        }else {
            EnumStatus status = personDTO.getStatus() == EnumStatus.ACTIVE.ordinal() ? EnumStatus.ACTIVE : EnumStatus.INACTIVE;
            person.setStatus(status );
        }
        return person;
    }
}

