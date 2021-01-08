package org.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springboot.entity.Produit;
import org.springboot.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProduitController {
	
	@Autowired
	private ProduitService produitService;
	
	@GetMapping("/")
	public String ShowElement(Model model) {
		
		/*
		 * List<Produit> produits = produitService.getAllProducts();
		 * model.addAttribute("produits", produits);
		 * return "index";
		 */
		
		String motCle = null;
		
		return findPaginated(1, model, "produitName", "asc", motCle);
	}
	
	@GetMapping("/newProduct")
	public String newProduct(Model model) {
		
		Produit produit = new Produit();
		model.addAttribute("produit", produit);
		
		return "newProduct";
	}
	
	@PostMapping("/saveProduit")
	public String save(@Valid @ModelAttribute("produit") Produit produit, Model model, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "newProduct";
		}
		
		produitService.saveProduct(produit);
		
		return "redirect:/";
	}
	
	@GetMapping("/editProduit/{id}") 
	public String editProduit(@PathVariable(name = "id") Long id, Model model) {
		
		Produit produit = produitService.getProductById(id);
		model.addAttribute("produit", produit);
		
		return "updateForm";
	}
	
	@GetMapping("/deleteProduit/{id}")
	public String delete(@PathVariable(name = "id") Long id) {
		
		produitService.deleteProductById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNb}")
	public String findPaginated(@PathVariable("pageNb") int pageNb, 
			                    Model model,
			                    @Param("sortField") String sortField,
			                    @Param("sortDir") String sortDir, 
			                    @Param("motCle") String motCle) {
		
		Page<Produit> page = produitService.findPaginated(pageNb, 4, sortField, sortDir, motCle);
		
		List<Produit> produits = page.getContent();
		int totalElements = (int)page.getTotalElements(); //getTotalElements return Long
		int totalPages = page.getTotalPages();
		
		model.addAttribute("produits", produits);                       //produits
		model.addAttribute("totalPages", totalPages);                   //page Totals
		model.addAttribute("totalElements", totalElements);             //totalElements
		model.addAttribute("currentPage", pageNb);                      //currentPage
		model.addAttribute("sortField", sortField); 
		model.addAttribute("sortDir", sortDir); 
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		
		model.addAttribute("motCle", motCle);
		
		
		return "index";
	}
	
}



















