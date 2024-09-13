package javaweb.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javaweb.Entity.MyUser;
import javaweb.Entity.Role;
import javaweb.Repository.MyUserRepository;

@Service
public class MyUserService implements UserDetailsService {
	
	@Autowired
	MyUserRepository repo;
	
	//NÀY DÀNH CHO PHÂN QUYỀN

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MyUser> user = repo.findByUsername(username);
		if (user.isPresent()) {
			var userObj = user.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(userObj.getPassword())
					.roles(getRoles(userObj))
					.build();
						
			
		}else {
			throw new UsernameNotFoundException(username);
		}
	}
	
	 private String[] getRoles(MyUser user) {
	        Set<String> roles = user.getRoles().stream()
	                .map(Role::getRoleName)
	                .collect(Collectors.toSet());
	        return roles.toArray(new String[0]);
	    }
}
