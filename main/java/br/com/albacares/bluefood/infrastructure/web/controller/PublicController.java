package br.com.albacares.bluefood.infrastructure.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.albacares.bluefood.application.ClienteService;
import br.com.albacares.bluefood.application.ValidationException;
import br.com.albacares.bluefood.domain.cliente.Cliente;
import br.com.albacares.bluefood.domain.restaurante.Restaurante;

@Controller
@RequestMapping(path = "/public")
public class PublicController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(path = "/cliente/new")
	public String newCliente(Model model) {
		
		Cliente cliente = new Cliente();		
		ControllerHelper.setEditMode(model, false);
		model.addAttribute("cliente", cliente);
		return "cliente-cadastro";
		
	}
	
	@GetMapping(path = "/restaurante/new")
	public String newRestaurante(Model model) {
		
		Restaurante restaurante = new Restaurante();		
		ControllerHelper.setEditMode(model, false);
		model.addAttribute("restaurante", restaurante);
		return "restaurante-cadastro";
		
	}
	
	@PostMapping(path = "/cliente/save")
	public String saveCliente(
			@ModelAttribute("cliente") @Valid Cliente cliente,
			Errors errors,
			Model model) {
		
		if (!errors.hasErrors()) {
			try {
				clienteService.saveCliente(cliente);
				model.addAttribute("msg", "Cliente gravado com sucesso!");
			
			} catch (ValidationException e) {
				errors.rejectValue("email", null, e.getMessage());
			}
		}
		
		ControllerHelper.setEditMode(model, false);
		return "cliente-cadastro";
	}

}
