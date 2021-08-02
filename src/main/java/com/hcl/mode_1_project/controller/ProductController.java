package com.hcl.mode_1_project.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
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
	public String updateProductForm(@PathVariable long id, ModelMap model, Authentication auth) {
		model.addAttribute("product", ps.getProductById(id));
		model.addAttribute("id", id);

		// admin users go to the "updateProduct" form page
		// unauthorized users need to be sent to the "updateQuantity" form or they will
		// not be able to submit their form.
		if (auth == null || !auth.isAuthenticated()) {
			return "redirect:/login";
		}
		boolean isAdmin = auth.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet())
				.contains("ROLE_ADMIN");
		return isAdmin ? "updateProduct" : "updateQuantity";
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
	public String createProduct(@Valid @ModelAttribute Product newProd, BindingResult result, ModelMap map) {
		System.out.println(result);
		System.out.println(newProd);
		if (result.hasErrors()) {
			System.out.println("error in update");
			map.addAttribute("error", result.toString());
			return "redirect:/400";
		}
		ps.addProduct(newProd);
		return "redirect:/products";
	}

	@PostMapping("/products/quant/{id}") // using post instead of put for html form
	public String updateQuantity(@PathVariable long id, @RequestParam int quant) throws ProductNotFoundException {
		System.out.println(id);
		Product toUpdate = ps.getProductById(id);
		toUpdate.setQuantity(quant);
		ps.updateProduct(toUpdate, id);
		return "redirect:/products";
	}

	@PostMapping("/products/{id}") // using post instead of put for html form
	public String updateProduct(@PathVariable long id, @Valid @ModelAttribute Product product, BindingResult result,
			ModelMap map) throws ProductNotFoundException {
		if (result.hasErrors()) {
			System.out.println("error in update");
			map.addAttribute("error", result.toString());
			return "redirect:/400";
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

	@GetMapping("/400")
	@ResponseBody
	public String badRequest(HttpServletRequest req) {
		return "Request Error: " + req.getAttribute("error");
	}
}
