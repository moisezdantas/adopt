package br.com.adopt.components;

import java.util.HashMap;
import java.util.Map;

import br.com.adopt.entity.Person;
import br.com.adopt.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import br.com.adopt.entity.User;
import br.com.adopt.repository.UserRepository;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PersonService personService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = userRepository.findByEmail(authentication.getName());
		Person person = personService.findPersonByUserId(user.getId());

		Map<String, Object> map = new HashMap<>();
		map.put("userId", user.getId());
		map.put("email", user.getEmail());
		map.put("userName", person.getName());
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		token.setAdditionalInformation(map);

		return accessToken;
	}

}
