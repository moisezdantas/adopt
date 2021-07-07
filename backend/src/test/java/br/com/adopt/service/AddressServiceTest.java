package br.com.adopt.service;


import br.com.adopt.dto.AddressDTO;
import br.com.adopt.entity.Address;
import br.com.adopt.factory.AddressFactory;
import br.com.adopt.repository.AddressRepository;
import br.com.adopt.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    private Address address;
    private Address address2;
    private Address addressSave;
    private AddressDTO addressDTO;
    private Long addressNotFound;
    private Long addressFound;

    @BeforeEach
    public void setup() throws Exception{
        address = AddressFactory.createBasic(1L);
        address2 = AddressFactory.createBasic(2L);
        addressSave= AddressFactory.createBasic(null);
        addressDTO = AddressFactory.createBasicDTO(null);
        addressNotFound = 1000L;
        addressFound = 1l;
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundException(){
        assertThrows(ResourceNotFoundException.class, () -> {
            addressService.findById(addressNotFound);
        });
        verify(addressRepository, times(1)).findById(addressNotFound);
    }

    @Test
    public void findByIdShouldReturnAddress(){
        when(addressRepository.findById(addressFound)).thenReturn(Optional.of(address));
        addressService.findById(addressFound);
        verify(addressRepository, times(1)).findById(addressFound);
    }

    @Test
    public void createShouldReturnAddress(){
        when(addressRepository.save(addressSave)).thenReturn(address);
        addressService.create(addressDTO);
        verify(addressRepository, times(1)).save(addressSave);
    }

}
