package com.generation.lojagames.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity                                             		
@Table(name = "tb_produtos")	
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotNull(message = "Nome é obrigatório!")                                       										
	private String nome;
	
	@Size(max=500)
	private String descricao;
	
	@NotNull(message = "Console é obrigatório!")
	private String console;
	
	private int quantidade;
	
	@Column(name = "data_lancamento")
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataLancamento;
	
	@NotNull(message = "Preço é obrigatório!")
	@Positive(message = "O preço deve ser maior do que zero!")
	private BigDecimal preco;
	
	private String foto;

	/**
	 * A anotação @Column além de renomear um atributo no Banco de dados,
	 * também permite definir um valor padrão (default), através da propriedade
	 * columnDefinition, onde deve ser informado o tipo do atributo (em nosso
	 * exemplo integer) e o valor padrão (em nosso exemplo 0)
	 */
	@Column(columnDefinition = "integer default 0")
	private int curtir;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public LocalDate getDataLancamento() {
		return this.dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public int getCurtir() {
		return this.curtir;
	}

	public void setCurtir(int curtir) {
		this.curtir = curtir;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}