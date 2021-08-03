package com.hcl.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcl.model.Product;
import com.hcl.service.ProductService;

import exception.ProductNotFoundException;

@Controller
@RequestMapping("")
public class ProductController {

	@Autowired
	ProductService ps;

	@GetMapping("/addProduct")
	public String addProductForm(ModelMap map) {
		map.addAttribute("product", new Product());
		return "newProduct";
	}

	@GetMapping("/updateProduct/{id}")
	public String updateProductForm(@PathVariable long id, ModelMap model, Authentication auth) {
		model.addAttribute("product", ps.getProductById(id));
		model.addAttribute("id", id);
		return "updateProduct";
	}

	@GetMapping("/updateQuantity/{id}")
	public String updateQuantityForm(@PathVariable long id, ModelMap model, Authentication auth) {
		model.addAttribute("product", ps.getProductById(id));
		model.addAttribute("id", id);
		return "updateQuantity";
	}

	@GetMapping("/products")
	public String showAllProducts(Model model, Principal principal) {
		List<Product> allProducts = ps.getAllProducts();
		model.addAttribute("products", allProducts);
		double total = allProducts.stream().map(p -> p.getPrice() * p.getQuantity()).reduce(0d, (t1, t2) -> t1 + t2);
		model.addAttribute("total", total);
		model.addAttribute("user", principal);
		return "allProducts";
	}

	@PostMapping("/products")
	public String createProduct(ModelMap map, @Valid @ModelAttribute(name = "product") Product product,
			BindingResult result) {
		System.out.println(result);
		System.out.println(product);
		if (result.hasErrors()) {
			return "newProduct";
		}
		ps.addProduct(product);
		return "redirect:/products";
	}

	@PostMapping("/products/quant/{id}") // using post instead of put for html form
	public String updateQuantity(@PathVariable long id, @Valid @ModelAttribute Product product, BindingResult result)
			throws ProductNotFoundException {
		System.out.println(id);
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
	@ResponseBody
	public String unauthorized() {
		return "unauthorized";
	}

}
