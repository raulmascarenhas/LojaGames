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

import com.example.LojaGames.Model.Produtos;
import com.example.LojaGames.Repository.CategoriaRepository;
import com.example.LojaGames.Repository.ProdutosRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "", allowedHeaders = "")

public class ProdutosController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired 
	private ProdutosRepository produtosRepository;
	
	@GetMapping
	public ResponseEntity<List<Produtos>> getAll(){
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Produtos> criarProdutos(@Valid @RequestBody Produtos produtos) {
	    if (produtosRepository.existsById(produtos.getId())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produtos já existe");
	    }
	    Produtos novoProdutos = produtosRepository.save(produtos);
	    return ResponseEntity.status(HttpStatus.CREATED).body(novoProdutos);
	}

	
	@GetMapping("/nomes/{nome}")
    public ResponseEntity<List<Produtos>> getProdutos(@PathVariable String nome){
        return ResponseEntity.ok(produtosRepository.findAllByNomeContainingIgnoreCase(nome));
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtos> obterProdutoPorId(@PathVariable Long id) {
	    Optional<Produtos> produto = produtosRepository.findById(id);
	    if (produto.isPresent()) {
	        return ResponseEntity.ok(produto.get());
	    }
	    return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
    public ResponseEntity<Produtos> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produtos produtosAtualizado) {
        Optional<Produtos> produtos = produtosRepository.findById(id);
        if (produtos.isPresent()) {
            produtosAtualizado.setId(id);
            Produtos produtosAtualizadoSalvo = produtosRepository.save(produtosAtualizado);
            return ResponseEntity.ok(produtosAtualizadoSalvo);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
	    Optional<Produtos> produtos = produtosRepository.findById(id);
	    if (produtos.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    }
	    produtosRepository.deleteById(id);
	}

	@PutMapping  
	public ResponseEntity <Produtos> put(@Valid @RequestBody Produtos produtos){
		if(categoriaRepository.existsById(produtos.getId())) {
			
			if(categoriaRepository.existsById(produtos.getId()))	
		return ResponseEntity.status(HttpStatus.OK)
				
				.body(produtosRepository.save(produtos));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "o Produto não existe", null);		
		}		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	
	
}
}	
	
