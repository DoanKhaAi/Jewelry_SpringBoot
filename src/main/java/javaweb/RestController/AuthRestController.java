package javaweb.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import javaweb.Entity.AuthRequest;
import javaweb.Entity.Cart;
import javaweb.Entity.MyUser;
import javaweb.Entity.RegisterUserDTO;
import javaweb.Jwt.JwtProvider;
import javaweb.Repository.CartRepository;
import javaweb.Repository.MyUserRepository;
import javaweb.Service.EmailService;
import javaweb.Service.MyUserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class AuthRestController {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private MyUserService myUserService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private MyUserRepository myuserRepository;
    
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            List<String> roles = authentication.getAuthorities()
                                               .stream()
                                               .map(GrantedAuthority::getAuthority)
                                               .collect(Collectors.toList());

            String token = jwtProvider.generateToken(authRequest.getUsername(), roles);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Đăng nhập không thành công");
        }
    }

    @PostMapping("api/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterUserDTO registerUserDTO,
            BindingResult bindingResult, HttpServletRequest request) throws IOException {

    	
    	Map<String, Object> response = new HashMap<>(); 

        if (myUserService.existsByUsername(registerUserDTO.getUsername())) {
            response.put("usernameError", "Tên đăng nhập đã được sử dụng. Bạn vui lòng chọn tên khác nhé!");
        }

        if (myUserService.existsByEmail(registerUserDTO.getEmail())) {
            response.put("emailError", "Email này đã được sử dụng. Bạn vui lòng chọn email khác nhé");
        }

        if (response.containsKey("usernameError") || response.containsKey("emailError")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        
        String otp = emailService.generateOtp();
        emailService.sendOtpEmail(registerUserDTO.getEmail(), otp);

        request.getSession().setAttribute("otp", otp);
        request.getSession().setAttribute("registerUserDTO", registerUserDTO);

        return ResponseEntity.ok((String)otp); //Chỗ này em trả về mã OTP để in trong console, cho mình dễ hơn trong việc nhập OTP để test
        
    }
    
    //==============================================================================================
    
    @PostMapping("api/verify-otp")
    public ResponseEntity<?> verifyOtp(
            @RequestBody String otp,
            HttpServletRequest request) throws IOException {

        String sessionOtp = (String) request.getSession().getAttribute("otp");
        RegisterUserDTO registerUserDTO = (RegisterUserDTO) request.getSession().getAttribute("registerUserDTO");


        if (otp.equals(sessionOtp + '=')) {
            myUserService.registerNewUser(registerUserDTO);

            request.getSession().removeAttribute("otp");
            request.getSession().removeAttribute("registerUserDTO");
            
        	MyUser user=myuserRepository.findByUsername(registerUserDTO.getUsername()).get();
            Cart cart = cartRepository.findByUser(user);
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                cartRepository.save(cart); 
            }


            return ResponseEntity.ok("OK");
        } else {
        	return ResponseEntity.ok("!OK");
        }
    }


}

