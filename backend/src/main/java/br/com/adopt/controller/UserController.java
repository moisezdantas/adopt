package br.com.adopt.controller;

import br.com.adopt.controller.util.ApiUtilOperation;
import br.com.adopt.dto.UserDTO;
import br.com.adopt.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "Find all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApiUtilOperation.MESSAGE_200),
            @ApiResponse(code = 400, message = ApiUtilOperation.MESSAGE_400),
            @ApiResponse(code = 401, message = ApiUtilOperation.MESSAGE_401),
            @ApiResponse(code = 403, message = ApiUtilOperation.MESSAGE_403),
            @ApiResponse(code = 404, message = ApiUtilOperation.MESSAGE_404),
            @ApiResponse(code = 500, message = ApiUtilOperation.MESSAGE_500),
    })
    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                 @RequestParam(value = "direction", defaultValue = "ASC") String direction ,
                                                 @RequestParam(value = "orderBy", defaultValue = "email") String orderBy
    ) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<UserDTO> listDto =  service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(listDto);
    }

    @ApiOperation(value = "Return an object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApiUtilOperation.MESSAGE_200),
            @ApiResponse(code = 400, message = ApiUtilOperation.MESSAGE_400),
            @ApiResponse(code = 401, message = ApiUtilOperation.MESSAGE_401),
            @ApiResponse(code = 403, message = ApiUtilOperation.MESSAGE_403),
            @ApiResponse(code = 404, message = ApiUtilOperation.MESSAGE_404),
            @ApiResponse(code = 500, message = ApiUtilOperation.MESSAGE_500),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO dto = service.findByUserId(id);
        return ResponseEntity.ok().body(dto);
    }

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
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO dto) {
        UserDTO dtoSave = service.createUser(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                 .path("/{id}").buildAndExpand(dtoSave.getId())
                 .toUri();
        return ResponseEntity.created(uri).body(dtoSave);
    }

    @ApiOperation(value = "Update an object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApiUtilOperation.MESSAGE_200),
            @ApiResponse(code = 400, message = ApiUtilOperation.MESSAGE_400),
            @ApiResponse(code = 401, message = ApiUtilOperation.MESSAGE_401),
            @ApiResponse(code = 403, message = ApiUtilOperation.MESSAGE_403),
            @ApiResponse(code = 404, message = ApiUtilOperation.MESSAGE_404),
            @ApiResponse(code = 500, message = ApiUtilOperation.MESSAGE_500),
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        UserDTO dtoSave = service.update(id, dto);;
        return ResponseEntity.ok().body(dtoSave);
    }

    @ApiOperation(value = "Delete an object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApiUtilOperation.MESSAGE_200),
            @ApiResponse(code = 400, message = ApiUtilOperation.MESSAGE_400),
            @ApiResponse(code = 401, message = ApiUtilOperation.MESSAGE_401),
            @ApiResponse(code = 403, message = ApiUtilOperation.MESSAGE_403),
            @ApiResponse(code = 404, message = ApiUtilOperation.MESSAGE_404),
            @ApiResponse(code = 500, message = ApiUtilOperation.MESSAGE_500),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
