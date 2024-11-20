package com.yourssu.openssupot.domain.domain.file

import java.io.File
import java.io.IOException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.mockito.Mockito.any
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile

@Suppress("NonAsciiCharacters")
@SpringBootTest
class LocalFileProcessorTest {

    @Autowired
    private val localFileUploader = LocalFileProcessor()

    private val mockFile = mock(MockMultipartFile::class.java)

    @Test
    fun `파일이 비어있는 경우 예외가 발생한다`() {
        // given
        val emptyFile = MockMultipartFile("empty.png", ByteArray(0))

        // when & then
        assertThatThrownBy { localFileUploader.upload(emptyFile) }
            .isInstanceOf(InvalidFileException::class.java)
            .hasMessage("파일이 비어있습니다.")
    }

    @Test
    fun `파일 이름이 비어있는 경우 예외가 발생한다`() {
        // given
        `when`(mockFile.originalFilename).thenReturn(null)

        // when & then
        assertThatThrownBy { localFileUploader.upload(mockFile) }
            .isInstanceOf(InvalidFileException::class.java)
            .hasMessage("파일 이름이 비어있습니다.")
    }

    @Test
    fun `지원하지 않는 파일 확장자이면 예외가 발생한다`() {
        // given
        `when`(mockFile.originalFilename).thenReturn("unsupported.abc")

        // when & then
        assertThatThrownBy { localFileUploader.upload(mockFile) }
            .isInstanceOf(UnsupportedFileExtensionException::class.java)
            .hasMessage("지원하지 않는 확장자입니다. : abc")
    }

    @Test
    fun `파일 저장에 실패한 경우 예외가 발생한다`() {
        // given
        `when`(mockFile.originalFilename).thenReturn("valid.png")
        `when`(mockFile.transferTo(any(File::class.java))).thenThrow(IOException::class.java)

        // when & then
        assertThatThrownBy { localFileUploader.upload(mockFile) }
            .isInstanceOf(StoreFailureException::class.java)
            .hasMessage("파일 저장에 실패했습니다.")
    }

    @Test
    fun `파일이 정상적으로 저장된 경우 파일 url을 반환한다`() {
        // given
        `when`(mockFile.originalFilename).thenReturn("valid.png")
        doNothing().`when`(mockFile).transferTo(any(File::class.java))

        // when
        val filePath = localFileUploader.upload(mockFile)

        // then
        assertThat(filePath).startsWith(localFileUploader.webApiPath)
    }
}
