package com.yourssu.openssupot.application.support.exception

import com.yourssu.openssupot.application.support.authentication.NoSuchOrganizationException
import com.yourssu.openssupot.domain.domain.authentication.PasswordNotMatchException
import com.yourssu.openssupot.domain.domain.file.InvalidFileException
import com.yourssu.openssupot.domain.domain.file.ReadFailureException
import com.yourssu.openssupot.domain.domain.file.StoreFailureException
import com.yourssu.openssupot.domain.domain.file.UnsupportedFileExtensionException
import com.yourssu.openssupot.domain.domain.organization.DuplicateEmailException
import com.yourssu.openssupot.domain.domain.organization.InvalidEmailException
import com.yourssu.openssupot.domain.domain.organization.InvalidOrganizationNameException
import com.yourssu.openssupot.domain.domain.organization.InvalidPasswordException
import com.yourssu.openssupot.domain.domain.organization.OrganizationNotFoundException
import com.yourssu.openssupot.domain.domain.organization.PasswordNotEncryptedException
import com.yourssu.openssupot.domain.support.security.password.PasswordEncodingFailureException
import com.yourssu.openssupot.domain.support.security.token.InvalidTokenException
import java.util.stream.Collectors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchOrganizationException::class)
    fun handleNoSuchOrganizationException(e: NoSuchOrganizationException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(PasswordNotMatchException::class)
    fun handlePasswordNotMatchException(e: PasswordNotMatchException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ExceptionResponse(e.message))
    }

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

    @ExceptionHandler(DuplicateEmailException::class)
    fun handleDuplicateEmailException(e: DuplicateEmailException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(InvalidEmailException::class)
    fun handleInvalidEmailException(e: InvalidEmailException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(InvalidOrganizationNameException::class)
    fun handleInvalidOrganizationNameException(e: InvalidOrganizationNameException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(InvalidPasswordException::class)
    fun handleInvalidPasswordException(e: InvalidPasswordException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(OrganizationNotFoundException::class)
    fun handleOrganizationNotFoundException(e: OrganizationNotFoundException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(PasswordNotEncryptedException::class)
    fun handlePasswordNotEncryptedException(e: PasswordNotEncryptedException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(PasswordEncodingFailureException::class)
    fun handlePasswordEncodingFailureException(e: PasswordEncodingFailureException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(InvalidTokenException::class)
    fun handleInvalidTokenException(e: InvalidTokenException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
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
