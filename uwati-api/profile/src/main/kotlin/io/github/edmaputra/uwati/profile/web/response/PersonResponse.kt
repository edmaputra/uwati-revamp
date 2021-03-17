package io.github.edmaputra.uwati.profile.web.response

import io.github.edmaputra.uwati.profile.enum.PersonType

data class PersonResponse(

    val id: String,

    val personId: String,

    val name: String,

    val email: String,

    val type: PersonType,

    val address: Map<String, Any>,

    val phone: String,

    val metadata: Map<String, Any>

)
