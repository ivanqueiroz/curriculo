package dev.ivanqueiroz.curriculo.dominio.usuario

import com.fasterxml.jackson.annotation.JsonIgnore
import dev.ivanqueiroz.curriculo.dominio.role.Role
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1, initialValue = 1)
    val id: Long,
    val nome: String,
    val login: String,
    val senha: String,

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USUARIO_ROLE")
    val role: List<Role> = mutableListOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Usuario

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , nome = $nome , login = $login )"
    }
}
