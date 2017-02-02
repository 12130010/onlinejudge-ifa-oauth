package onlinejudge.oauth.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import onlinejudge.domain.User;

public class MyUserDetailBuilder {
	public static UserDetails createUserDetailFromDomain(User domainUser){
		return new UserDetails() {
			private static final long serialVersionUID = -1444575712080637984L;

			@Override
			public boolean isEnabled() {
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return !domainUser.isLock();
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return !domainUser.isLock();
			}
			
			@Override
			public String getUsername() {
				return domainUser.getEmail();
			}
			
			@Override
			public String getPassword() {
				return domainUser.getPassword();
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				List<String> roles = domainUser.getRoles();
				List<GrantedAuthority> listGrant = new ArrayList<>();
				for (String role : roles) {
					listGrant.add(new SimpleGrantedAuthority(role));
				}
				return listGrant;
			}
		};
	}
}
