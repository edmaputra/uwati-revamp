package io.github.edmaputra.uwati.profile.web

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @Value("\${user.role}")
    private val role: String? = null

    @GetMapping("/")
    fun index(): String? {
        return role;
    }
}