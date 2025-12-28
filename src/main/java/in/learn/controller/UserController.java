package in.learn.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import in.learn.entity.Users;
import in.learn.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping("register/users")
	public String register(@RequestBody Users user) {
		return service.register(user);
	}
	
	@PostMapping("login/users")
    public Users login(@RequestBody Users user) {
        return service.login(user.getEmail(), user.getPassword());
    }
	
	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgot(@RequestParam String email) {

	    String result = service.forgotPassword(email);

	    if ("Email not registered".equals(result)) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(result);
	    }

	    return ResponseEntity.ok(result);
	}
		

    @PostMapping("/reset-password")
    public String reset(
            @RequestParam String email,
            @RequestParam String otp,
            @RequestParam String newPassword) {

        return service.resetPassword(email, otp, newPassword);
    }
    
    @GetMapping("/users/{id}")
    public Users getUserById(@PathVariable long id) {
        return service.getUserById(id);
    }
    
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return service.getAllUsers();
    }

}
