package br.com.adopt.factory;

import br.com.adopt.dto.AddressDTO;
import br.com.adopt.entity.Address;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddressFactory {

    public static Set<Address> createListBasic(){
        return new HashSet<>(List.of(createBasic(null)));
    }

    public static Address createBasic(Long id){
        return new Address(id, "Zona Leste", "SP", "00000-000", "Rua Test", "ap 11b", "12");
    }

    public static AddressDTO createBasicDTO(Long id){
        return new AddressDTO(id, "Zona Leste", "SP", "00000-000", "Rua Test", "ap 11b", "12");
    }
}
