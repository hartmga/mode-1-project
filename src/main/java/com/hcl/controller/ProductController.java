package com.hcl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hcl.exception.ProductNotFoundException;
import com.hcl.model.Product;
import com.hcl.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService ps;

	@GetMapping("/products")
	public String showAllProducts(Model model, Authentication auth) {
		List<Product> allProducts = ps.getAllProducts();
		model.addAttribute("products", allProducts);
		double total = ps.getProductTotal(allProducts);
		model.addAttribute("total", total);
		model.addAttribute("user", auth.getPrincipal());
		return "allProducts";
	}

	@GetMapping("/addProduct")
	public String addProductForm(ModelMap map) {
		map.addAttribute("product", new Product());
		return "newProduct";
	}

	@GetMapping("/updateProduct/{id}")
	public String updateProductForm(@PathVariable long id, ModelMap model) throws ProductNotFoundException {
		model.addAttribute("product", ps.getProductById(id));
		model.addAttribute("id", id);
		return "updateProduct";
	}

	@GetMapping("/updateQuantity/{id}")
	public String updateQuantityForm(@PathVariable long id, ModelMap model) throws ProductNotFoundException {
		model.addAttribute("product", ps.getProductById(id));
		model.addAttribute("id", id);
		return "updateQuantity";
	}

	@PostMapping("/products")
	public String createProduct(ModelMap map, @Valid @ModelAttribute(name = "product") Product product,
			BindingResult result) {
		if (result.hasErrors()) {
			return "newProduct";
		}
		ps.addProduct(product);
		return "redirect:/products";
	}

	@PostMapping("/products/quant/{id}") // using post instead of put for html form
	public String updateQuantity(@PathVariable long id, @Valid @ModelAttribute Product product, BindingResult result)
			throws ProductNotFoundException {
		if (result.hasErrors()) {
			if (result.hasFieldErrors("quantity")) {
				return "updateQuantity";
			}
		}
		Product toUpdate = ps.getProductById(id);
		toUpdate.setQuantity(product.getQuantity());
		ps.updateProduct(toUpdate, id);
		return "redirect:/products";
	}

	@PostMapping("/products/{id}") // using post instead of put for html form
	public String updateProduct(@PathVariable long id, @Valid @ModelAttribute Product product, BindingResult result,
			ModelMap map) throws ProductNotFoundException {
		if (result.hasErrors()) {
			return "updateProduct";
		}
		ps.updateProduct(product, id);
		return "redirect:/products";
	}

	@GetMapping("/products/delete/{id}")
	public String deleteProductById(@PathVariable long id) throws ProductNotFoundException {
		ps.deleteProduct(id);
		return "redirect:/products";
	}

	@GetMapping("/403")
	public String unauthorized() {
		return "unauthorized";
	}

}
