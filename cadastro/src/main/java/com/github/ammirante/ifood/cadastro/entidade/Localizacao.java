package com.github.ammirante.ifood.cadastro.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Localizacao
 *
 */
@Entity
@Table(name = "localizacao")
public class Localizacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public Double latitute;
	public Double longitude;
}
