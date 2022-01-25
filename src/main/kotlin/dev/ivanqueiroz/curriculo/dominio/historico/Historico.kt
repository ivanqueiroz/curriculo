package dev.ivanqueiroz.curriculo.dominio.historico

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class Historico(
    var titulo: String = "",
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HISTORICO")
    @SequenceGenerator(name = "SEQ_HISTORICO", sequenceName = "SEQ_HISTORICO", allocationSize = 1, initialValue = 1)
    var id: Long = 0,
    var instituicao: String = "",
    var descricao: String = "",
    var anoInicio: String = "",
    var anoFim: String = "",
    @Column(name = "LINK_REFERENCIA")
    var linkReferencia: String = "",
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_HISTORICO")
    var tipoHistorico: TipoHistorico = TipoHistorico.NENHUM
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Historico

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , titulo = $titulo , tipoHistorico = $tipoHistorico )"
    }
}
