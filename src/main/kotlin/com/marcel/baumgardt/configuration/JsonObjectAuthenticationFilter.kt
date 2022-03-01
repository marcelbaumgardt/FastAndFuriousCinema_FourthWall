package com.marcel.baumgardt.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.marcel.baumgardt.model.security.LoginCredentials
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JsonObjectAuthenticationFilter(
    private val objectMapper: ObjectMapper
) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        return try {
            val reader = request.reader
            val sb = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                sb.append(line)
            }
            val authRequest = objectMapper.readValue(sb.toString(), LoginCredentials::class.java)
            val token = UsernamePasswordAuthenticationToken(
                authRequest.username, authRequest.password
            )
            setDetails(request, token)
            authenticationManager.authenticate(token)
        } catch (e: IOException) {
            throw IllegalArgumentException(e.message)
        }
    }
}