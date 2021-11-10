package com.doncorleone.dondelivery.services;

import com.doncorleone.dondelivery.domain.Produto;
import com.doncorleone.dondelivery.repositories.ProdutoRepository;
import com.doncorleone.dondelivery.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;

	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	@Transactional(readOnly = true)
	public List<Produto> findAll() {
		 List<Produto> list = repo.findAll();
		return list;
	}
}
