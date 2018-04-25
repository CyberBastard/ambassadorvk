package com.lanzdev.core.facades.vkpost.converters.impl

import com.lanzdev.MockitoExtension
import com.lanzdev.core.data.DocAttachmentData
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import com.vk.api.sdk.objects.docs.Doc
import com.vk.api.sdk.objects.wall.WallpostAttachment
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockitoExtension::class)
internal class DocAttachmentConverterTest {

    private val testData: DocAttachmentData = DocAttachmentData(1, "TITLE", 2, "EXT", "URL")

    private val wallpostAttachment: WallpostAttachment = mock()
    private val doc : Doc = mock()

    private val docAttachmentConverter = DocAttachmentConverter()

    @BeforeEach
    fun `set up`() {
        reset(wallpostAttachment, doc)
        whenever(wallpostAttachment.doc).thenReturn(doc)
    }

    @Test
    fun `should populate id`() {
        whenever(doc.id).thenReturn(testData.id)
        val actual = docAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.id).isEqualTo(testData.id)
    }

    @Test
    fun `should populate title`() {
        whenever(doc.title).thenReturn(testData.title)
        val actual = docAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.title).isEqualTo(testData.title)
    }

    @Test
    fun `should populate size`() {
        whenever(doc.size).thenReturn(testData.size)
        val actual = docAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.size).isEqualTo(testData.size)
    }

    @Test
    fun `should populate ext`() {
        whenever(doc.ext).thenReturn(testData.ext)
        val actual = docAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.ext).isEqualTo(testData.ext)
    }

    @Test
    fun `should populate url`() {
        whenever(doc.url).thenReturn(testData.url)
        val actual = docAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.url).isEqualTo(testData.url)
    }
}
