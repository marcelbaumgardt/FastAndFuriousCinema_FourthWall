package com.marcel.baumgardt.exception

import com.marcel.baumgardt.model.dto.response.TechnicalErrorResponse
import com.marcel.baumgardt.model.dto.response.TechnicalErrorStatus
import lombok.extern.log4j.Log4j2
import org.apache.logging.log4j.LogManager
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Log4j2
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    fun handleException(ex: Exception): TechnicalErrorResponse {
        log.error("Unexpected application error", ex)
        return TechnicalErrorResponse(
            TechnicalErrorStatus.TECHNICAL_ERROR,
            ex.message.orEmpty()
        )
    }

    companion object {
        private val log = LogManager.getLogger()
    }
}