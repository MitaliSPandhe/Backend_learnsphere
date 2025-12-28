package in.learn.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.learn.constants.Role;
import in.learn.dao.UserDao;
import in.learn.entity.Users;

@Service
public class UserService {

	@Autowired
	UserDao dao;

	@Autowired
	SendGridEmailService emailService;

	public String register(Users user) {
		if (user.getRole() == null) {
			user.setRole(Role.STUDENT);
		}

		if (user.getRole() != null) {
			String roleStr = user.getRole().toString().toUpperCase();
			user.setRole(Role.valueOf(roleStr));
		}

		boolean isRegistered = dao.register(user);

		if (isRegistered) {
			return "Registration Successfull....!!";
		} else {
			return "Registration Failed....!!";
		}
	}

	public Users login(String email, String password) {

		Users user = dao.findByEmail(email);

		if (user == null) {
			throw new RuntimeException("User not found");
		}

		if (!user.getPassword().equals(password)) {
			throw new RuntimeException("Invalid password");
		}

		return user;
	}

	public String forgotPassword(String email) {

		Users user = dao.findByEmail(email);

		if (user == null) {
			return "Email not registered";
		}

		String otp = String.valueOf((int) (Math.random() * 900000) + 100000);

		user.setResetToken(otp);
		user.setTokenExpiry(LocalDateTime.now().plusMinutes(10));

		dao.updateUser(user);

		emailService.sendOtpEmail(email, otp);

		return "OTP sent to registered email";
	}
	

	public String resetPassword(String email, String otp, String newPassword) {

		Users user = dao.findByEmail(email);

		if (user == null)
			return "Invalid email";

		if (user.getResetToken() == null || !otp.equals(user.getResetToken()))
			return "Invalid OTP";

		if (user.getTokenExpiry() == null || user.getTokenExpiry().isBefore(LocalDateTime.now()))
			return "OTP expired";

		user.setPassword(newPassword);

		user.setResetToken(null);
		user.setTokenExpiry(null);

		dao.updateUser(user);

		return "Password reset successful";
	}

	public Users getUserById(long id) {
		return dao.findById(id);
	}

	public List<Users> getAllUsers() {
		return dao.findAll();
	}

}
