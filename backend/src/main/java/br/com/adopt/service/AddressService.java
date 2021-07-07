package br.com.adopt.service;

import br.com.adopt.dto.AddressDTO;
import br.com.adopt.entity.Address;
import br.com.adopt.repository.AddressRepository;
import br.com.adopt.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    /**
     * Method for create address
     * @param addressDTO
     * @return AddressDTO
     */
    @Transactional
    public AddressDTO create(AddressDTO addressDTO){
        Address address = createAddress(addressDTO);
        return new AddressDTO(address);
    }

    /**
     * Method for create address
     * @param addressDTO
     * @return Address
     */
    @Transactional
    public Address createAddress(AddressDTO addressDTO){
        Address address = copyDtoToEntity(addressDTO, new Address());
        return addressRepository.save(address);
    }

    /**
     * Method for find address by id
     * @param id
     * @return AddressDTO
     */
    @Transactional(readOnly = true)
    public AddressDTO findById(Long id){
        Address address = findSourceById(id);
        return new AddressDTO(address);
    }

    /**
     * Method for find address by id
     * @param id
     * @return AddressDTO
     */
    @Transactional(readOnly = true)
    public Address findSourceById(Long id){
        Optional<Address> addressSource = addressRepository.findById(id);
        Address address = addressSource.orElseThrow(() -> new ResourceNotFoundException("Entity not found: " + id));
        return address;
    }

    /**
     * Method for copy dto to entity
     * @param dto
     * @param address
     * @return Address
     */
    private Address copyDtoToEntity(AddressDTO dto, Address address){
        address.setId(dto.getId());
        address.setCity(dto.getCity());
        address.setComplement(dto.getComplement());
        address.setDistrict(dto.getDistrict());
        address.setZipCode(dto.getZipCode());
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        return address;
    }
}
