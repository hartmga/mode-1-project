package com.hcl.mode_1_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcl.mode_1_project.model.Product;
import com.hcl.mode_1_project.service.ProductService;

import exception.ProductNotFoundException;

@Controller
public class ProductController {

	@Autowired
	ProductService ps;

	@GetMapping("/addProduct")
	public String addProductForm(ModelMap map) {
		map.addAttribute("product", new Product());
		return "newProduct";
	}

	@GetMapping("/updateProduct/{id}")
	public String updateProductForm(@PathVariable long id, ModelMap model) {
		model.addAttribute("product", ps.getProductById(id));
		model.addAttribute("id", id);
		return "updateProduct";
	}

	@GetMapping("/products")
	public String showAllProducts(Model model) {
		List<Product> allProducts = ps.getAllProducts();
		model.addAttribute("products", allProducts);
		double total = allProducts.stream().map(p -> p.getPrice() * p.getQuantity()).reduce(0d, (t1, t2) -> t1 + t2);
		model.addAttribute("total", total);
		return "allProducts";
	}

	@PostMapping("/products")
	public String createProduct(@ModelAttribute Product newProd, BindingResult result) {
		// TODO add error checking
		ps.addProduct(newProd);
		return "redirect:/products";
	}

	@PostMapping("/products/{id}") // using post instead of put for html form
	public String updateProduct(@PathVariable long id, @ModelAttribute Product product)
			throws ProductNotFoundException {
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
