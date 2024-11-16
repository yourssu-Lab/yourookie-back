package com.yourssu.openssupot.application.support.exception

import com.yourssu.openssupot.domain.domain.file.InvalidFileException
import com.yourssu.openssupot.domain.domain.file.ReadFailureException
import com.yourssu.openssupot.domain.domain.file.StoreFailureException
import com.yourssu.openssupot.domain.domain.file.UnsupportedFileExtensionException
import java.util.stream.Collectors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFileException::class)
    fun handleInvalidFileException(e: InvalidFileException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(UnsupportedFileExtensionException::class)
    fun handleUnsupportedFileExtensionException(e: UnsupportedFileExtensionException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(StoreFailureException::class)
    fun handleStoreFailureException(e: StoreFailureException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(ReadFailureException::class)
    fun handleReadFailureException(e: ReadFailureException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        e: MethodArgumentNotValidException,
    ): ResponseEntity<ExceptionResponse> {
        val message = e.fieldErrors
            .stream()
            .map { obj: FieldError -> obj.defaultMessage }
            .collect(Collectors.joining("\n"))

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(message))
    }
}
