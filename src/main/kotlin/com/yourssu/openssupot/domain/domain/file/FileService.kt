package com.yourssu.openssupot.domain.domain.file

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service

@Service
class FileService {

    companion object {
        private const val FILE_PROTOCOL_PREFIX = "file:"
    }

    @Value("\${file.upload.path}")
    lateinit var fileStoreDir: String

    fun read(storeName: String): Resource {
        val storePath = fileStoreDir + storeName
        val file = UrlResource(FILE_PROTOCOL_PREFIX + storePath)
        validateFile(file, storePath)

        return file
    }

    private fun validateFile(file: UrlResource, storePath: String) {
        val fileName = storePath.substring(fileStoreDir.length)
        if (!file.exists() || !file.isReadable) {
            throw ReadFailureException("파일[$fileName]이 존재하지 않거나 읽을 수 없습니다.")
        }
    }
}
