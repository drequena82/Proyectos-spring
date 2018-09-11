package io.spring.billing.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.spring.billing.entities.Bill;
import io.spring.billing.entities.Client;
import io.spring.billing.managers.BillManager;
import io.spring.billing.managers.ClientManager;

@Controller
@RequestMapping("client")
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
	public ModelAndView getClients(	) {
		Iterable<Client> clients = manager.findAll();
		ModelAndView mod = new ModelAndView("client-list");
		mod.addObject("clients",clients);
		
		return mod;
	}
	
	@GetMapping("/new")
	public String newClient(Client client ) {
		return "client-form";
	}
	
	@PostMapping("/confirmNew")
	public String confirmNewClient(Client client ,RedirectAttributes redirect) {
		try {
			manager.save(client);
		}catch(Exception ex) {
			redirect.addAttribute("Error al insertar un nuevo cliente");
		}
		return "redirect:client-list";
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
