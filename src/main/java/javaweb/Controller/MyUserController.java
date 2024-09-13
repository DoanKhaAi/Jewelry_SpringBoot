package javaweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyUserController {
	
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "/login";
	}
}