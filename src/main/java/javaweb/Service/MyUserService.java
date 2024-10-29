package javaweb.Service;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import javaweb.Entity.MyUser;
import javaweb.Entity.RegisterUserDTO;
import javaweb.Entity.Role;
import javaweb.Repository.MyUserRepository;
import javaweb.Repository.RoleRepository;

@Service
public class MyUserService implements UserDetailsService {
	
	@Autowired
	MyUserRepository repo;
	
	@Autowired
	RoleRepository repoRole;
	
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
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
	 
	 //NGƯỜI DÙNG + QUYỀN
	 public List<MyUser> findAllUsers() {
			return repo.findAll();
	 }
	 
	 public MyUser findById(Long id) {
			return repo.findById(id).orElse(null);
	 }
	 
	 public void updateUserRoles(Long userID, Set<Long> roleIDs) {
	        MyUser user = findById(userID);
	        Set<Role> roles = new HashSet<>();
	        for (Long roleID : roleIDs) {
	            Role role = repoRole.findById(roleID).get();
	            roles.add(role);
	        }
	        user.setRoles(roles);
	        repo.save(user);
	 }
	 
	 //==========================================================================
	 	public boolean existsByUsername(String username) {
	        return repo.findByUsername(username).isPresent();
	    }
	 	
	 //=================================================================================

	    public boolean existsByEmail(String email) {
	        return repo.findByEmail(email) != null;
	    }
	 
	 //============================================================================
	 
	 @Transactional
	    public MyUser registerNewUser(RegisterUserDTO registerUserDTO) throws IOException {
	        MyUser user = new MyUser();

	        user.setUsername(registerUserDTO.getUsername());
	        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
	        user.setFullname(registerUserDTO.getFullname());
	        user.setEmail(registerUserDTO.getEmail());
	        user.setGender(registerUserDTO.getGender()); 
	        user.setBirthday(registerUserDTO.getBirthday()); 
//	        Role roleUser = new Role("USER");
//	        user.getRoles().add(roleUser); 
	        
	        Role userRole = repoRole.findByRoleName("USER");
	        if (userRole != null) {
	            Set<Role> roles = new HashSet<>();
	            roles.add(userRole);
	            user.setRoles(roles);
	        }

	        return repo.save(user); 
	    }

}
