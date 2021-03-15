package com.internship.internship.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.internship.internship.dao.RoleDao;
import com.internship.internship.entity.ProfileImage;
import com.internship.internship.entity.UserRole;
import com.internship.internship.entity.UsersModel;

@Controller
public class AuthenticationController {

	@Autowired
	private RoleDao rd;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/login")
	public String showLogin() {
		return "loginpage";
	}
	
	@GetMapping("/register/showForm")
	public String showForm(Model theModel) {
		UsersModel u=new UsersModel();
		theModel.addAttribute("users",u);
		return "registration";
	}
	
	@PostMapping("/register/save")
	public String registerUser(@ModelAttribute("users") UsersModel user,@RequestParam("pimage") MultipartFile file) throws IOException {
		user.setPassword(encoder.encode(user.getPassword()));
		UserRole ur=new UserRole();
		ProfileImage pi=new ProfileImage();
		pi.setName(file.getOriginalFilename());
		pi.setType(file.getContentType());
		pi.setData(file.getBytes());
		user.setImage(pi);
		ur.setUser(user);
		rd.save(ur);
		return "confirm";
	}
}
