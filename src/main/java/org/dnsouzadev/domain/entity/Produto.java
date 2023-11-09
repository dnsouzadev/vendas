package org.dnsouzadev.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    public Produto(Integer id, String descricao, BigDecimal preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto() {}

    @Column(name = "descricao")
    @NotEmpty(message = "Campo descricao e obrigatorio")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "preco e obrigatorio")
    private BigDecimal preco;

}