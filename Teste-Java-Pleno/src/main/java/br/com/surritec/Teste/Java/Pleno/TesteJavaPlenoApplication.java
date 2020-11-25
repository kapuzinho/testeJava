package br.com.surritec.Teste.Java.Pleno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.*"})
@EnableJpaRepositories(basePackages = {"br.com.surritec.repositorios"})
@EnableTransactionManagement
@EntityScan(basePackages = {"br.com.surritec."})
public class TesteJavaPlenoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TesteJavaPlenoApplication.class, args);
	}

}
