package br.com.adopt.factory;
import br.com.adopt.dto.PersonDTO;
import br.com.adopt.entity.*;

import java.util.Set;

public class PersonFactory {

    private static User user;
    private static Set<Address> addressList;

    public static  Person createPersonBasic(Long id, EnumStatus status){
        user = UserFactory.createUserBasic(1L);
        addressList = AddressFactory.createListBasic();

        Person person = new Person(id, user, "Teste","Teste","11 1521-7475",
                "1234567890","307669800", EnumGender.MAN, EnumPersonType.PHYSICAL, null, addressList, status);
        return person;
    }

    public static PersonDTO createPersonBasicDTO(Long id){
        Person person = createPersonBasic(null, EnumStatus.ACTIVE);
        return new PersonDTO(person);
    }
}
