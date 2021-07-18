package br.com.adopt.controller;

import br.com.adopt.controller.util.ApiUtilOperation;
import br.com.adopt.controller.util.SecuredUtil;
import br.com.adopt.dto.AnimalTypeDTO;
import br.com.adopt.dto.BreedDTO;
import br.com.adopt.service.AnimalTypeService;
import br.com.adopt.service.BreedService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/breed")
public class BreedController {

    @Autowired
    private BreedService service;

    @ApiOperation(value = "Find all breed")
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
    public ResponseEntity<List<BreedDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
}
