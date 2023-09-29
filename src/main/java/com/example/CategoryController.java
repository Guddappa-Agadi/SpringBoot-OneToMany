package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class CategoryController {

	@Autowired
	private CategoryRepo cr;
	
	@PostMapping("/api/category")
	public ResponseEntity<Category> saveData(@RequestBody Category c){
		return new ResponseEntity<>(cr.save(c), HttpStatus.CREATED);
	}
	@GetMapping("/api/category")
	public ResponseEntity<List<Category>> getData(){
		List<Category> lst = cr.findAll();
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}
	
	@GetMapping("/api/category/{id}")
	public ResponseEntity<Category> getDataById(@PathVariable int cat_id){
		Optional<Category> obj= cr.findById(cat_id);
		if(obj.isPresent()) {
			return new ResponseEntity<>(obj.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	@PutMapping("/api/category/{id}")
	public ResponseEntity<Category> updateDataById(@PathVariable int cat_id, @RequestBody Category c){
		Optional<Category> obj= cr.findById(cat_id);
		if(obj.isPresent()) {
			obj.get().setCat_name(c.getCat_name());
			obj.get().setCat_stocks(c.getCat_stocks());
			obj.get().setProduct(c.getProduct());
			return new ResponseEntity<>(cr.save(obj.get()), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	@DeleteMapping("/api/category/{id}")
	public ResponseEntity<Category> deleteDataById(@PathVariable int cat_id){
		Optional<Category> obj= cr.findById(cat_id);
		if(obj.isPresent()) {
			cr.deleteById(cat_id);
			return new ResponseEntity<>(obj.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@GetMapping("/api/category/page")
	public List<Category> page(){
		Pageable p = PageRequest.of(0, 10, Sort.by("cat_id").ascending());
		Page<Category> pa = cr.findAll(p);
		return pa.getContent();
	}
}
