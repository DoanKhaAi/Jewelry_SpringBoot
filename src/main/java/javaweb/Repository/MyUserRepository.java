package javaweb.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaweb.Entity.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
	
	
	Optional<MyUser> findByUsername(String username);

    MyUser findByEmail(String email);
}
