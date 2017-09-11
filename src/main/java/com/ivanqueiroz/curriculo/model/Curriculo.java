package com.ivanqueiroz.curriculo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Ivan Queiroz <ivanqueiroz@gmail.com>
 */
@Entity
public class Curriculo implements Serializable {

    private static final long serialVersionUID = -2900842519899417795L;

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String resumo;

    @OneToMany
    private List<Instituicao> academico;

    @OneToMany
    private List<Certificacao> certificacoes;

    @OneToMany
    private List<Cargo> experiencia;

    @OneToMany
    private List<Contato> contatos;

    @OneToMany
    private List<Conhecimento> conhecimentos;

    @OneToMany
    private List<Lingua> linguas;

    @ElementCollection
    @CollectionTable(name="Passatempos", joinColumns = @JoinColumn(name = "curriculo_id"))
    private List<String> passatempos;

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

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public List<Instituicao> getAcademico() {
        return academico;
    }

    public void setAcademico(List<Instituicao> academico) {
        this.academico = academico;
    }

    public List<Certificacao> getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(List<Certificacao> certificacoes) {
        this.certificacoes = certificacoes;
    }

    public List<Cargo> getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(List<Cargo> experiencia) {
        this.experiencia = experiencia;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Conhecimento> getConhecimentos() {
        return conhecimentos;
    }

    public void setConhecimentos(List<Conhecimento> conhecimentos) {
        this.conhecimentos = conhecimentos;
    }

    public List<Lingua> getLinguas() {
        return linguas;
    }

    public void setLinguas(List<Lingua> linguas) {
        this.linguas = linguas;
    }

    public List<String> getPassatempos() {
        return passatempos;
    }

    public void setPassatempos(List<String> passatempos) {
        this.passatempos = passatempos;
    }

}
