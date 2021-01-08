package org.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springboot.entity.Produit;
import org.springboot.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProduitServiceImpl implements ProduitService {

	@Autowired
	private ProduitRepository produitRepository;
	
	@Override
	public List<Produit> getAllProducts() {
		return produitRepository.findAll();
	}

	@Override
	public Produit getProductById(long id) {
		
		Optional<Produit> optional = produitRepository.findById(id);
		
		Produit produit= null;
		
		if (optional.isPresent()) {
			produit = optional.get();
		} else {
			throw new RuntimeException(" Produit not found for id :: " + id);
		}
		return produit;
	}

	@Override
	public void saveProduct(Produit prod) {
		produitRepository.save(prod);
	}

	@Override
	public void deleteProductById(long id) {
		produitRepository.deleteById(id);
	}

	@Override
	public Page<Produit> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String motCle) {

		Sort sort = Sort.by(sortField);//.ascending();
		sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);  //Pageable est 1e Interface
		
		
		  if (motCle != null) {
			  return produitRepository.findByProduitNameContains(motCle, pageable);//search(motCle, pageable); 
		  }
		 
		return produitRepository.findAll(pageable);
	}

}


























