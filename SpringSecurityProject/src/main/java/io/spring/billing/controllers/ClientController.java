package io.spring.billing.controllers;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.spring.billing.entities.Audit;
import io.spring.billing.entities.Bill;
import io.spring.billing.entities.Client;
import io.spring.billing.managers.BillManager;
import io.spring.billing.managers.ClientManager;

@Controller
@RequestMapping("client")
@SessionAttributes("client")
public class ClientController {
	
	@Autowired
	private ClientManager manager;
	
	@Autowired
	private BillManager billManager;
	
	@GetMapping("{id}/details")
	public String getClient(@PathVariable("id") String id,Model model) {
		Client client = manager.findOne(Long.valueOf(id)).get();
		List<Bill> bills = billManager.fetchByClientIdWithLineWithProduct(Long.valueOf(id));
		client.setBills(bills);
		
		Map<String,Client> map = new HashMap<String,Client>();
		map.put("client", client);
		model.mergeAttributes(map);
		
		return "client-details";
	}
	
	@GetMapping("/list")
	public ModelAndView getClients() {
		Iterable<Client> clients = manager.findAll();
		ModelAndView mod = new ModelAndView("client-list");
		mod.addObject("clients",clients);
		/*
		if(message!= null) {
			mod.addObject("message",message);
		}
		
		if(code!= null) {
			mod.addObject("resultCode",code);
		}
		*/
		return mod;
	}
	
	@GetMapping("/new")
	public String newClient(Client client ,Model model,BindingResult result) {
		model.asMap().put("client",client);
		return "client-form";
	}
	
	@PostMapping("/save")
	public String saveClient(@Valid Client client ,Model model,BindingResult result,RedirectAttributes redirect) {
		String red = "redirect:list";
		
		try {
			if(result.hasErrors()) {
				model.asMap().put("client",client);
				red = "redirect:new";
				redirect.asMap().put("message", "Faltan campos del cliente");
				redirect.asMap().put("resultCode", "0");
			}else {
				client.setAudit(new Audit());
				client.getAudit().setCreateOn(new Date(System.currentTimeMillis()));
				manager.save(client);
				redirect.asMap().put("message", "Cliente guardado con exito");
				redirect.asMap().put("resultCode", "1");
			}
		}catch(Exception ex) {
			model.asMap().put("client",client);
			redirect.asMap().put("mensaje","Error al insertar un nuevo cliente");
			redirect.asMap().put("resultCode", "0");
			red = "redirect:new";
		}
		return red;
	}
	
	@PostMapping("/delete/id={id}")
	public String deleteClient(@RequestParam("id") String id,RedirectAttributes redirect) {
		Client client = manager.findOne(Long.valueOf(id)).get();
		
		try {
			manager.delete(client);
		}catch(Exception ex) {
			redirect.addAttribute("Error al eliminar");
		}
		return "redirect:client-list";
	}
}
