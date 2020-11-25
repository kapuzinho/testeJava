package br.com.surritec.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import br.com.surritec.to.EnderecoTO;

@Controller
public class ViaCepController implements Serializable{

	private static final long serialVersionUID = 2747151937142071632L;

	@RequestMapping(value="/cep/{cep}", method = RequestMethod.GET)
	public ResponseEntity<EnderecoTO> obterEndereco(@PathVariable(name = "cep") String cep){
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://viacep.com.br/ws/{cep}/json/";
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("cep", cep);
	    EnderecoTO enderecoTO = restTemplate.getForObject(url, EnderecoTO.class, params);
		return new ResponseEntity<EnderecoTO>(enderecoTO, HttpStatus.OK);
	}
}
