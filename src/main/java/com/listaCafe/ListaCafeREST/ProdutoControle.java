package com.listaCafe.ListaCafeREST;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutoControle {

	@Autowired
	private ProdutoServico servico;

	@GetMapping("/produtos")
	public List<Produto> lista() {
		return servico.listarTodos();
	}

	@GetMapping("/produtos/{id}")
	public ResponseEntity<Produto> get(@PathVariable Integer id) {
		try {
			Produto produto = servico.get(id);
			return new ResponseEntity<Produto>(produto, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/produtos")
	public ResponseEntity<Produto> add(@RequestBody Produto produto) {
		List<Produto> listaProdutos = servico.listarTodos();
		for (Produto produto2 : listaProdutos) {
			if(produto2.getItemCafe().equals(produto.getItemCafe().toUpperCase()) 
					|| produto2.getCpf().equals(produto.getCpf())) {
				return new ResponseEntity<Produto>(HttpStatus.BAD_REQUEST); 
			}
		}
		produto.setId(new Integer(1));
		produto.setItemCafe(produto.getItemCafe().toUpperCase());
		servico.salvar(produto);
		return new ResponseEntity<Produto>(HttpStatus.CREATED);
	}

	@PutMapping("/produtos/{id}")
	public ResponseEntity<?> update(@RequestBody Produto produto, @PathVariable Integer id) {
		try {
			Produto existProduto = servico.get(id);
			if (existProduto != null) {
				existProduto.setColaborador(produto.getColaborador());
				existProduto.setCpf(produto.getCpf());
				existProduto.setItemCafe(produto.getItemCafe().toUpperCase());
			} else {
				return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
			}
			servico.update(existProduto);
			
			

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}
	
	@DeleteMapping("/produtos/{id}")
	public void delete(@PathVariable Integer id) {
		servico.deletar(id);
		
	}
}
