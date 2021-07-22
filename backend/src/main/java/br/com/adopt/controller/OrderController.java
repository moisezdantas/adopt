package br.com.adopt.controller;

import br.com.adopt.controller.util.ApiUtilOperation;
import br.com.adopt.controller.util.SecuredUtil;
import br.com.adopt.dto.AnimalDTO;
import br.com.adopt.dto.OrderDTO;
import br.com.adopt.service.AnimalService;
import br.com.adopt.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @ApiOperation(value = "Insert a new object into the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApiUtilOperation.MESSAGE_200),
            @ApiResponse(code = 400, message = ApiUtilOperation.MESSAGE_400),
            @ApiResponse(code = 401, message = ApiUtilOperation.MESSAGE_401),
            @ApiResponse(code = 403, message = ApiUtilOperation.MESSAGE_403),
            @ApiResponse(code = 404, message = ApiUtilOperation.MESSAGE_404),
            @ApiResponse(code = 500, message = ApiUtilOperation.MESSAGE_500),
    })
    @PostMapping
    public ResponseEntity<OrderDTO> save(@Valid @RequestBody OrderDTO dto) {
        OrderDTO dtoSave = service.create(dto);
        return ResponseEntity.ok(dtoSave);
    }
}
