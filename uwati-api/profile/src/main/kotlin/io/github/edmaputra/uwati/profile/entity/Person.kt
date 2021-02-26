package io.github.edmaputra.uwati.profile.entity

import io.github.edmaputra.uwati.profile.enum.PersonType
import java.util.*
import javax.persistence.*

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: UUID,

    val personId: String,

    val name: String,

    val email: String,

    @Enumerated(EnumType.STRING)
    val type: PersonType,

    val address: String,

    val phone: String,

    @Lob
    val metadata: String?
)