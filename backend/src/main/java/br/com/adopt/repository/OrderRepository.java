package br.com.adopt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adopt.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{ 

}
