package br.com.surritec.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.surritec.entidades.Administrador;
import br.com.surritec.entidades.Clientes;
import br.com.surritec.repositorios.AdministradoresRepository;
import br.com.surritec.repositorios.ClienteRepository;

@Controller
public class AdministradoresController {

	@Autowired
	AdministradoresRepository administradoresRepository;
	
	@Autowired
	ClienteRepository clienteRepository;

	@SuppressWarnings("null")
	@RequestMapping(method = RequestMethod.POST, value = "/entrar")
	public ModelAndView logar(Administrador entrada) {

		List<Administrador> admins = (List<Administrador>) administradoresRepository.findAll();		
		System.out.println("Entrada Usuário:___" + entrada.getUsuario());
		System.out.println("Entrada Senha:___" + entrada.getSenha());

		for (int i = 0; i < admins.size(); i++) {
			System.out.println("Dentro for usuario:___" + admins.get(i).getUsuario().toString());
			System.out.println("Dentro for senha:___" + admins.get(i).getSenha().toString());
			if (admins.get(i).getUsuario().equalsIgnoreCase(entrada.getUsuario()) && 
				admins.get(i).getSenha().equalsIgnoreCase(entrada.getSenha())) {
				return logado();					
			}
		}
		return Notlogado();
	}
	
	public ModelAndView logado() {
		ModelAndView andView = new ModelAndView("logado/logado.html");
		Iterable<Clientes> clientesInt = clienteRepository.findAll();
		andView.addObject("clientes", clientesInt);
		return andView;
	}
	
	public ModelAndView Notlogado() {
		ModelAndView andView = new ModelAndView("logar/login.html");
		String erro = "1";
		andView.addObject("erro", erro);
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String login() {
		return "logar/login.html";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/logar")
	public String login2() {
		return "logar/login.html";
	}
}
