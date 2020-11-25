package br.com.surritec.repositorios;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.surritec.entidades.Administrador;

@Repository
@Transactional
public interface AdministradoresRepository extends CrudRepository<Administrador, Long>{
	
}
