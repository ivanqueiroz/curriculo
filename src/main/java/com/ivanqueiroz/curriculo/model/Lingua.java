package com.ivanqueiroz.curriculo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Ivan Queiroz <ivanqueiroz@gmail.com>
 */
@Entity
public class Lingua implements Serializable {

    private static final long serialVersionUID = -6016521986922192962L;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nome);
        hash = 37 * hash + Objects.hashCode(this.nivel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lingua other = (Lingua) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.nivel != other.nivel) {
            return false;
        }
        return true;
    }

}
