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
import lombok.Data;

/**
 *
 * @author Ivan Queiroz <ivanqueiroz@gmail.com>
 */
@Data
@Entity
public class Curriculo implements Serializable {

    private static final long serialVersionUID = -2900842519899417795L;

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String profissao;
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
    @CollectionTable(name = "Passatempos", joinColumns = @JoinColumn(name = "curriculo_id"))
    private List<String> passatempos;

    public String getNomeCurto() {
        if (nome != null && !nome.isEmpty()) {
            String[] nomes = nome.split(" ");
            if (nomes.length > 1) {
                return nomes[0] + " " + nomes[nomes.length - 1];
            }
        }
        return nome;
    }

}
