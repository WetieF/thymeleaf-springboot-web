package org.springboot.service;

import java.util.List;

import org.springboot.entity.Produit;
import org.springframework.data.domain.Page;

public interface ProduitService {
	
	public List<Produit> getAllProducts(); 
	public Produit getProductById(long id); 
	public void saveProduct(Produit prod); 
	public void deleteProductById(long id); 
	Page<Produit> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String motCle);
	
}
