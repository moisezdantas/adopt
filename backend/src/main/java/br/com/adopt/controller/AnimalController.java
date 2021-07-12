package br.com.adopt.controller;

import br.com.adopt.controller.util.ApiUtilOperation;
import br.com.adopt.controller.util.SecuredUtil;
import br.com.adopt.dto.AnimalDTO;
import br.com.adopt.dto.UserDTO;
import br.com.adopt.service.AnimalService;
import br.com.adopt.service.UserService;
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

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @ApiOperation(value = "Find all animal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApiUtilOperation.MESSAGE_200),
            @ApiResponse(code = 400, message = ApiUtilOperation.MESSAGE_400),
            @ApiResponse(code = 401, message = ApiUtilOperation.MESSAGE_401),
            @ApiResponse(code = 403, message = ApiUtilOperation.MESSAGE_403),
            @ApiResponse(code = 404, message = ApiUtilOperation.MESSAGE_404),
            @ApiResponse(code = 500, message = ApiUtilOperation.MESSAGE_500),
    })
    @GetMapping
    @Secured({SecuredUtil.ROLE_ADMIN, SecuredUtil.ROLE_USER})
    public ResponseEntity<Page<AnimalDTO>> findAll(@RequestParam(value = "animalTypeId", defaultValue = "0") Long animalTypeId,
                                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                   @RequestParam(value = "direction", defaultValue = "ASC") String direction ,
                                                   @RequestParam(value = "orderBy", defaultValue = "email") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<AnimalDTO> listDto =  service.findAllAnimalTypePaged(animalTypeId, pageRequest);
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
    @Secured({SecuredUtil.ROLE_ADMIN, SecuredUtil.ROLE_USER})
    public ResponseEntity<AnimalDTO> findById(@PathVariable Long id) {
        AnimalDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
