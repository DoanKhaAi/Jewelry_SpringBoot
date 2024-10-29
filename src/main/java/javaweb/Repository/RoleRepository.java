package javaweb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaweb.Entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
    Role findByRoleName(String name);
}
