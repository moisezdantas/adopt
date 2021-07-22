package br.com.adopt.service;

import br.com.adopt.dto.AddressDTO;
import br.com.adopt.dto.OrderDTO;
import br.com.adopt.entity.Address;
import br.com.adopt.entity.Order;
import br.com.adopt.entity.Person;
import br.com.adopt.repository.AddressRepository;
import br.com.adopt.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PersonService personService;

    /**
     * Method for create address
     * @param dto
     * @return OrderDTO
     */
    @Transactional
    public OrderDTO create(OrderDTO dto){

        Order order = new Order();
        order.setDescription(dto.getDescription());
        order.setPrice(Double.valueOf(dto.getPrice()));
        order.setPayerID(dto.getPayerID());
        order.setPaymentId(dto.getPaymentId());
        order.setSku(dto.getSku());
        Person person = personService.findPersonByUserId(dto.getUser().getId());
        order.setStatus("APPROVED");
        order.setPerson(person);
        order = orderRepository.save(order);
        return new OrderDTO(order);
    }
}
