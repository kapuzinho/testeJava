package br.com.surritec.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.com.surritec.entidades.Clientes;
import br.com.surritec.repositorios.ClienteRepository;

public class ClienteDAO implements ClienteRepository {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void saveOrUpdate(Clientes cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Clientes cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public Clientes ProcurarId(Long id) {
		return hibernateTemplate.get(Clientes.class, id);
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
}
