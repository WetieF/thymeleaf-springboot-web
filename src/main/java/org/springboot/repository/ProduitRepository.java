package org.springboot.repository;

import org.springboot.entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	 
	
	/*
	 * @Query("SELECT p FROM produits p WHERE" +
	 * " CONCAT(p.id, '', p.produitName, '', p.madeIn, '', p.price)" + " LIKE %?1%")
	 */
     // public  Page<Produit> search(String motCle, Pageable pageable);
	 
	
	  public Page<Produit> findByProduitNameContains(String mc, Pageable pageable);
}
