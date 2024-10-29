package javaweb.Entity;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterUserDTO {

// Em chú thích phần này vì em đã chuyển qua validate ở phía client bằng js

//	  @NotBlank(message = "Username is required")
//    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    private String username;

//    @NotBlank(message = "Password is required")
//    @Size(min = 8, message = "Password must be at least 8 characters long")
//    @Pattern(
//        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", 
//        message = "Password must contain at least one letter, one number, and one special character"
//    )
    private String password;

//    @NotBlank(message = "Full name is required")
    private String fullname;

//    @Email(message = "Email should be valid")
//    @NotBlank(message = "Email is required")
    private String email;

    private LocalDate birthday; 
    private Integer gender;    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public RegisterUserDTO() {

	}
	public RegisterUserDTO(
			@NotBlank(message = "Username is required") @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters") String username,
			@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password must contain at least one letter, one number, and one special character") String password,
			@NotBlank(message = "Full name is required") String fullname,
			@Email(message = "Email should be valid") @NotBlank(message = "Email is required") String email,
			LocalDate birthday, Integer gender) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
	}

	
    
}
