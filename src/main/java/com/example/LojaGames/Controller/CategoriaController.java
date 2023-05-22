package com.example.LojaGames.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.LojaGames.Model.Categoria;
import com.example.LojaGames.Repository.CategoriaRepository;

import jakarta.validation.Valid;

@RequestMapping ("/categorias")
@RestController
@CrossOrigin (origins = "*", allowedHeaders ="*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	

	
	@GetMapping
	public ResponseEntity <List<Categoria>> getAll(){
		return ResponseEntity.ok(categoriaRepository.findAll());
		
			
	}
	@GetMapping("/{id}")  
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		
				
	}
	
	@GetMapping ("/generos/{genero}")
	public ResponseEntity <List<Categoria>> getByTitulo(@PathVariable String genero){
		return ResponseEntity.ok(categoriaRepository.findAllByGeneroContainingIgnoreCase(genero));
		
	}	
	
	
	@PostMapping
	public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria) {
	    if (categoriaRepository.existsById(categoria.getId())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria já existe");
	    }
	    Categoria novaCategoria = categoriaRepository.save(categoria);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
	    if (categoriaRepository.existsById(id)) {
	        Categoria categoriaExistente = categoriaRepository.findById(id).orElse(null);
	        if (categoriaExistente != null) {
	          
	            Categoria categoriaAtualizada = categoriaRepository.save(categoriaExistente);
	            return ResponseEntity.status(HttpStatus.OK).body(categoriaAtualizada);
	        }
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada");
	    }
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@ResponseStatus (HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		Optional<Categoria> postagem = categoriaRepository.findById(id);
		
		if(postagem.isEmpty())
			throw new ResponseStatusException (HttpStatus.NOT_FOUND);
		categoriaRepository.deleteById(id);
		
		
	}

	@PutMapping  
	public ResponseEntity <Categoria> put(@Valid @RequestBody Categoria categoria){
		if(categoriaRepository.existsById(categoria.getId())) {
			
			if(categoriaRepository.existsById(categoria.getId()))	
		return ResponseEntity.status(HttpStatus.OK)
				
				.body(categoriaRepository.save(categoria));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe", null);		
		}		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	
}
}
	