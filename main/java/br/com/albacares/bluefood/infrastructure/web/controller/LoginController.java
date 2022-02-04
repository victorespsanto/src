package br.com.albacares.bluefood.infrastructure.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	
	@GetMapping({"/login", "/"})
	public String login() {
		return "login";
	}
	
	@GetMapping({"/login-error"})
	public String loginError(Model model) {
		model.addAttribute("msg", "Credenciais inválidas");
		return "login";
	}

}
