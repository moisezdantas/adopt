package br.com.adopt.factory;

import br.com.adopt.dto.RoleDTO;
import br.com.adopt.dto.UserDTO;
import br.com.adopt.entity.User;

import java.util.Collection;

public class UserFactory {

    public static UserDTO createUser(String email, String password, Collection<RoleDTO> roles){
        UserDTO dto = new UserDTO(email, password);
        dto.getRoles().addAll(roles);
        return dto;
    }


    public static User createUser(Long id, String name, String password){
        return new User(id, name, password);
    }

    public static User createUserBasic(Long id){
        User user = new User(id, "test@gmail.com", "password");
        return user;
    }

    public static UserDTO createUserBasicDTO(Long id){
        UserDTO dto = new UserDTO("test@gmail.com", "password");
        dto.setId(id);
        dto.getRoles().addAll(RoleFactory.createRolesUserDTO());
        return dto;
    }

}
