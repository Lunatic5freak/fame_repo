package com.internship.internship.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.internship.internship.dao.UserDao;
import com.internship.internship.entity.ProfileImage;
import com.internship.internship.entity.UsersModel;

@Controller
public class UserController {
	
	@Autowired
	private UserDao ud;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/")
	public String showHome(Principal principal,Model theModel) {
		String name=principal.getName();
		UsersModel u=ud.findById(name).get();
		theModel.addAttribute("user",u);
		return "homepage";
	}
	
	@GetMapping("/user/update")
	public String updateinfo(Principal principal,Model theModel) {
		String name=principal.getName();
		UsersModel u=ud.findById(name).get();
		System.out.println(u.getEmail());
		theModel.addAttribute("userinfo",u);
		return "update";
	}
	
	@PostMapping("/user/updateinfo")
	public String update(@ModelAttribute UsersModel user,Model theModel,@RequestParam("photo") MultipartFile file) throws IOException {
		ProfileImage pi=new ProfileImage();
		pi.setName(file.getOriginalFilename());
		pi.setType(file.getContentType());
		pi.setData(file.getBytes());
		user.setImage(pi);
		ud.save(user);
		return "redirect:/";
	}
	
	@GetMapping("/user/showImage")
	public ResponseEntity<byte[]> showImage(Principal principal){
		String name=principal.getName();
		UsersModel u=ud.findById(name).get();
		ProfileImage pi=u.getImage();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(pi.getType())).body(pi.getData());
	}
	
	@GetMapping("/user/changepassword")
	public String changepass() {
		return "changepassword";
	}
	
	@PostMapping("/user/changepass")
	public String passchange(Principal principal,@RequestParam("new-password") String password,@RequestParam("old-password") String oldPass,HttpSession session) {
		String name=principal.getName();
		UsersModel u=ud.findById(name).get();
		if(encoder.matches(oldPass, u.getPassword())) {
			u.setPassword(encoder.encode(password));
			ud.save(u);
			session.setAttribute("success", "done");
			return "redirect:/";
		}
		session.setAttribute("failed", "wrong");
		return "changepassword";
	}
	
	
}
