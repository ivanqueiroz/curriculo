package dev.ivanqueiroz.curriculo.dominio.conhecimento

import javax.persistence.*

@Entity
data class Conhecimento (
        var titulo: String ="",

        var nivel: Float = 0.0F,

        @Enumerated(EnumType.STRING)
        @Column(name = "TIPO_CONHECIMENTO")
        var tipoConhecimento: TipoConhecimento = TipoConhecimento.NENHUM,

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONHECIMENTO")
        @SequenceGenerator(name = "SEQ_CONHECIMENTO", sequenceName = "SEQ_CONHECIMENTO", allocationSize = 1, initialValue = 1)
        var id: Long = 0
)