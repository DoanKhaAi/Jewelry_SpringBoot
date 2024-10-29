package javaweb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javaweb.Entity.Role;
import javaweb.Repository.RoleRepository;


@Service
public class RoleService {

	@Autowired
	private RoleRepository repo;
	
	public List<Role> list() {
        return (List<Role>) repo.findAll();
    }
	
	public void save(Role role) {
	     repo.save(role);
	}

    public Role getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void update(Role role) {
	    Role existingRole = repo.findById(role.getRoleID()).get();
	    existingRole.setRoleName(role.getRoleName());
	    repo.save(existingRole);
    }
    
    public void delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
    }
}
