package javaweb.Entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private Long roleID;
	
	@Column(name="role_name", length=50, nullable=false)
	private String roleName;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private Set<MyUser> users = new HashSet<>();;

	public Role() {
		
	}

	public Long getRoleID() {
		return roleID;
	}

	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<MyUser> getUsers() {
		return users;
	}

	public void setUsers(Set<MyUser> users) {
		this.users = users;
	}


	
}
