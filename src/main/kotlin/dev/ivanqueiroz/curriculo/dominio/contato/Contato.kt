package dev.ivanqueiroz.curriculo.dominio.contato

import javax.persistence.*

@Entity
data class Contato(

        var valor: String = "",

        var descricao: String = "",

        @Enumerated(EnumType.STRING)
        @Column(name = "TIPO_CONTATO")
        var tipoContato: TipoContato = TipoContato.NENHUM,

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTATO")
        @SequenceGenerator(name = "SEQ_CONTATO", sequenceName = "SEQ_CONTATO", allocationSize = 1, initialValue = 1)
        var id: Long = 0L
)
