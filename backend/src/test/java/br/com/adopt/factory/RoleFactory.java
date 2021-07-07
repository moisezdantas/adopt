package br.com.adopt.factory;

import br.com.adopt.dto.RoleDTO;
import br.com.adopt.entity.Role;

import java.util.ArrayList;
import java.util.Collection;

public class RoleFactory {

    public static Role createRole(Long id, String authority){
        return new Role(id, authority);
    }

    public static Collection<RoleDTO> createRolesUserDTO(){
        Collection<RoleDTO> roles = new ArrayList<>();
        RoleDTO role = new RoleDTO(1l, "ROLE_OPERATOR");
        roles.add(role);
        return roles;
    }
}
