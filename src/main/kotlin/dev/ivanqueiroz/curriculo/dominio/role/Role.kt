package dev.ivanqueiroz.curriculo.dominio.role

import org.hibernate.Hibernate
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROLE")
    @SequenceGenerator(name = "SEQ_ROLE", sequenceName = "SEQ_ROLE", allocationSize = 1, initialValue = 1)
    private val id: Long,
    private val nome: String
) : GrantedAuthority {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Role

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }

    override fun getAuthority() = nome
}
