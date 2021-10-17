package com.listaCafe.ListaCafeREST;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServico {

	@Autowired
	private ListaCafeRepository repo;

	public List<Produto> listarTodos() {
		return repo.findAll();
	}

	public void salvar(Produto produto) {

		repo.save(produto);
	}

	public Produto get(Integer id) {
		return repo.getOne(id);
	}

	public void deletar(Integer id) {
		repo.deleteById(id);
	}

	public void update(Produto produto) {
		repo.save(produto);
	}

}
