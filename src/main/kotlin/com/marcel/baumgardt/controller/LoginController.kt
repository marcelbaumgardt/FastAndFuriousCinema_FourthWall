package com.marcel.baumgardt.controller

import com.marcel.baumgardt.model.security.LoginCredentials
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController {

    @PostMapping("/login")
    fun login(@RequestBody credentials: LoginCredentials) {
    }
}