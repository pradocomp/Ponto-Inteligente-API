package com.projeto.pontointeligente.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.projeto.pontointeligente.api.enums.TipoEnum;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "lancamento")
@Data
@ToString
@NoArgsConstructor
public class Lancamento implements Serializable {
	
	private static final long serialVersionUID = 4837216188338228996L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false)
	private Date data;
	@Column(name = "descricao", nullable = true)
	private String descricao;
	@Column(name = "localizacao", nullable = true)
	private String localizacao;
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	private TipoEnum tipo;
	@ManyToOne(fetch = FetchType.EAGER)
	private Funcionario funcionario;
	
	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

}