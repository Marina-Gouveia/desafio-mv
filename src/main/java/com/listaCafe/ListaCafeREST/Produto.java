package com.listaCafe.ListaCafeREST;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String colaborador;
	private String cpf;
	private String itemCafe;

	public Produto(String colaborador, String cpf, String itemCafe) {
		super();
		this.colaborador = colaborador;
		this.cpf = cpf;
		this.itemCafe = itemCafe;
	}

	public Produto() {

	}
	
	public int getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getColaborador() {
		return colaborador;
	}

	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getItemCafe() {
		return itemCafe;
	}

	public void setItemCafe(String itemCafe) {
		this.itemCafe = itemCafe;
	}

}
