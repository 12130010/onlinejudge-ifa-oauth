package onlinejudge.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import onlinejudge.domain.User;
import onlinejudge.oauth.util.MyUserDetailBuilder;
import onlinejudge.repository.UserRepository;

@Service
public class MongoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User domainUser = repository.findUserByEmail(username);

		if (domainUser == null) {
			throw new UsernameNotFoundException(username);
		}

		return MyUserDetailBuilder.createUserDetailFromDomain(domainUser);
	}
}