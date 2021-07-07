package br.com.adopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adopt.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
