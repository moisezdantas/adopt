package br.com.adopt.service;

import br.com.adopt.dto.RoleDTO;
import br.com.adopt.dto.UserDTO;
import br.com.adopt.entity.Role;
import br.com.adopt.repository.RoleRepository;
import br.com.adopt.service.exception.ResourceNotFoundException;
import br.com.adopt.service.exception.UserDuplicateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.adopt.entity.User;
import br.com.adopt.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * Method for return list users
	 * @param page
	 * @return Page<UserDTO>
	 */
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(PageRequest page) {
		Page<User> list = repository.findAll(page);
		return list.map(x -> new UserDTO(x));
	}

	/**
	 * Method for create user
	 * @param dto
	 * @return UserDTO
	 */
	@Transactional
	public UserDTO createUser(UserDTO dto){
		UserDetails userFind = repository.findByEmail(dto.getEmail());
		if (userFind != null){
			logger.error("User duplicate", userFind.getUsername());
			throw new UserDuplicateException("User duplicate " + dto.getEmail());
		}
		User user = new User( null, dto.getEmail(), dto.getPassword());

		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		getRole(dto, user);
		user = repository.save(user);

		return new UserDTO(user);
	}

	/**
	 * Method for find user by id
	 * @param id
	 * @return UserDTO
	 */
	@Transactional(readOnly = true)
	public UserDTO findByUserId(Long id){
		User user = findUserId(id);
		return new UserDTO(user);
	}

	/**
	 * Method to updated user by id
	 * @param id
	 * @param dto
	 * @return UserDTO
	 */
	@Transactional
	public UserDTO update(Long id, UserDTO dto){
		User userExist = findUserId(id);
		userExist.setEmail(dto.getEmail());
		if(dto.getPassword() != null){
			userExist.setPassword(passwordEncoder.encode(dto.getPassword()));
		}
		repository.saveAndFlush(userExist);
		return new UserDTO(userExist);
	}

	/**
	 * Method delete user by id
	 * @param id
	 */
	@Transactional
	public void deleteById(Long id) {
		User userFind = findUserId(id);
		repository.deleteById(id);
	}

	/**
	 * Method for find user by id
	 * @param id
	 * @return User
	 */
	@Transactional(readOnly = true)
	private User findUserId(Long id){
		Optional<User> userFind = repository.findById(id);
		return userFind.orElseThrow(() -> new ResourceNotFoundException("Entity not found:" + id));
	}

	/**
	 * Method copy dto to entity
	 * @param dto
	 * @param entity
	 */
	private void getRole(UserDTO dto, User entity) {
		entity.getRoles().clear();
		for (RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRepository.getOne(roleDto.getId());
			entity.getRoles().add(role);
		}
	}

	/**
	 * Method to search user by email
	 * @param email
	 * @return UserDetails
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByEmail(email);
		if(user == null){
			logger.error("User not found", email);
			throw new UsernameNotFoundException("Email not found");
		}
		return user;
	}

}