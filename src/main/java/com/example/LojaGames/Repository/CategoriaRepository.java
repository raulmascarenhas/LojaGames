package com.example.LojaGames.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.LojaGames.Model.Categoria;

public interface CategoriaRepository extends JpaRepository <Categoria, Long >{

	public List <Categoria> findAllByGeneroContainingIgnoreCase(@Param("genero") String genero);
}
