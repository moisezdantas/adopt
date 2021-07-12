package br.com.adopt.service;

import br.com.adopt.dto.UserDTO;
import br.com.adopt.entity.Role;
import br.com.adopt.entity.User;
import br.com.adopt.factory.RoleFactory;
import br.com.adopt.factory.UserFactory;
import br.com.adopt.repository.RoleRepository;
import br.com.adopt.repository.UserRepository;
import br.com.adopt.service.exception.ResourceNotFoundException;
import br.com.adopt.service.exception.UserDuplicateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private User user;
    private User user2;
    private Role roleUser;
    private UserDTO userDTO;
    private String password;
    private Long userNotFound;
    private Long userFound;

    private PageRequest request;
    private Page<User> pages;

    @BeforeEach
    public void setup() throws Exception{
        userDTO = UserFactory.createUserBasicDTO(null );
        user = UserFactory.createUserBasic(1L);
        user2 = UserFactory.createUserBasic(2L);
        roleUser = RoleFactory.createRole(1L, "ROLE_OPERATOR");
        password = "xutfop9";
        userNotFound = 1000L;
        userFound = 1l;
        request = PageRequest.of(0, 12, Sort.Direction.ASC, "email");
        pages = new PageImpl<>(List.of(user, user2));

    }

    @Test
    public void findAllPagedShouldReturnUsersPaged() {
        when(userRepository.findAll(request)).thenReturn(pages);
        userService.findAllPaged(request);
        verify(userRepository, times(1)).findAll(request);
    }

    @Test
    public void createUserShouldThrowReturnUserDuplicateException(){
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(user);
        assertThrows(UserDuplicateException.class, () -> {
            userService.createUser(userDTO);
        });
    }

    @Test
    public void shouldReturnCreateUser(){
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn(password);
        when(userRepository.save(any())).thenReturn(user);
        when(roleRepository.getOne(any())).thenReturn(roleUser);
        userService.createUser(userDTO);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundException(){
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.findByUserId(userNotFound);
        });
        verify(userRepository, times(1)).findById(userNotFound);
    }

    @Test
    public void findByIdShouldReturnUser(){
        when(userRepository.findById(userFound)).thenReturn(Optional.of(user));
        userService.findByUserId(userFound);
        verify(userRepository, times(1)).findById(userFound);
    }

    @Test
    public void updateShouldThrowReturnNotFoundException(){
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.update(userNotFound, userDTO);
        });
        verify(userRepository, times(1)).findById(userNotFound);
    }

    @Test
    public void updateShouldReturnUser(){
        when(userRepository.findById(userFound)).thenReturn(Optional.of(user));
        userService.update(userFound, userDTO);
        verify(userRepository, times(1)).findById(userFound);
        //verify(userRepository, times(1)).save(any());
    }

    @Test
    public void deleteByIdShouldThrowReturnNotFoundException(){
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.deleteById(userNotFound);
        });
        verify(userRepository, times(1)).findById(userNotFound);
    }

    @Test
    public void deleteByIdShouldSuccess(){
        when(userRepository.findById(userFound)).thenReturn(Optional.of(user));
        userService.deleteById(userFound);
        verify(userRepository, times(1)).deleteById(userFound);
    }

}
