package br.com.adopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adopt.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{ 

}
