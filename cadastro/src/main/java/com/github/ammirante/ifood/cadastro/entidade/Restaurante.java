package com.github.ammirante.ifood.cadastro.entidade;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * 
 * Restaurante
 *
 */
@Entity
@Table(name = "restaurante")
public class Restaurante extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
		
	public String proprietario;
	public String nome;
	
	public String cnpj;
	
	@OneToOne(cascade = CascadeType.ALL)
	public Localizacao localizacao; 
	
	@CreationTimestamp
	public Date dataCriacao;
	
	@UpdateTimestamp
	public Date dataAtualizacao;
}
