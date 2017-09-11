package com.ivanqueiroz.curriculo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Ivan Queiroz <ivanqueiroz@gmail.com>
 */
@Entity
public class Cargo implements Serializable {

    private static final long serialVersionUID = 7507206094926918198L;

    @Id
    @GeneratedValue
    private long id;

    private String nome;
    private String empresa;
    private String descricao;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date anoInicio;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date anoFim;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(Date anoInicio) {
        this.anoInicio = anoInicio;
    }

    public Date getAnoFim() {
        return anoFim;
    }

    public void setAnoFim(Date anoFim) {
        this.anoFim = anoFim;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.anoInicio);
        hash = 23 * hash + Objects.hashCode(this.anoFim);
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
        final Cargo other = (Cargo) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.anoInicio, other.anoInicio)) {
            return false;
        }
        if (!Objects.equals(this.anoFim, other.anoFim)) {
            return false;
        }
        return true;
    }

}
