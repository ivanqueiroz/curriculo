package com.ivanqueiroz.curriculo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Ivan Queiroz <ivanqueiroz@gmail.com>
 */
@Entity
public class Conhecimento implements Serializable {

    private static final long serialVersionUID = -670298989351240073L;

    enum Nivel {
        INICIANTE(new BigDecimal("0.25")),
        PROFICIENTE(new BigDecimal("0.50")),
        ESPECIALISTA(new BigDecimal("0.75")),
        MESTRE(new BigDecimal("1.00"));

        private Nivel(BigDecimal valor) {
            this.valor = valor;
        }

        private BigDecimal valor;

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

    }

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private Nivel nivel;

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

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

}
