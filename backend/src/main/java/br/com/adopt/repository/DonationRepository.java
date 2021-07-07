package br.com.adopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adopt.entity.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long>{ 

}
