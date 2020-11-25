package br.com.surritec.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import br.com.surritec.entidades.Clientes;
import br.com.surritec.repositorios.ClienteRepository;

@Controller
public class ClientesController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/listaClientes")
	public ModelAndView clientes() {
		ModelAndView andView = new ModelAndView("logado/logado.html");
		Iterable<Clientes> clientesInt = clienteRepository.findAll();
		andView.addObject("clientes", clientesInt);
		return andView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/salvarCliente")
	public ModelAndView salvar(Clientes cliente) {
		cliente.setCpf(cliente.getCpf().replaceAll("\\D", ""));
		cliente.setTelefone(cliente.getTelefone().replaceAll("\\D", ""));
		clienteRepository.save(cliente);
		return clientes();
	}
	
	@RequestMapping(value="/editarCliente", method = RequestMethod.POST)
	public ModelAndView alterar(@RequestParam Long id) {
		System.out.println("ID:___"+ id);
		ModelAndView andView = new ModelAndView("modal/modalEditarCliente.html");
		Clientes cliente = clienteRepository.findById(id).get();
		andView.addObject("cliente", cliente);
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/atualizar")
	public ModelAndView atualizar(Clientes clienteEntrada,@RequestParam Long id) {
		Clientes cliente = clienteRepository.findById(id).get();
		cliente.setCpf(clienteEntrada.getCpf().replaceAll("\\D", ""));
		cliente.setEmail(clienteEntrada.getEmail());
		cliente.setBairro(clienteEntrada.getBairro());
		cliente.setCidade(clienteEntrada.getCidade());
		cliente.setRua(clienteEntrada.getRua());
		cliente.setUf(clienteEntrada.getUf());
		cliente.setNome(clienteEntrada.getNome());
		cliente.setTelefone(clienteEntrada.getTelefone().replaceAll("\\D", ""));
		clienteRepository.save(cliente);
		return clientes();
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/excluirCliente")
	public ModelAndView deletar(@RequestParam Long id) {
		System.out.println("cliente delete id: " + id);
		clienteRepository.deleteById(id);
		return clientes();
	}
	
	@RequestMapping(value="/sair")
	public String sair() {
		return "logar/login.html";
	}
}
