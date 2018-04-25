package com.lanzdev

import com.lanzdev.core.CustomWallpostAttachmentType
import com.lanzdev.core.facades.vkpost.AttachmentDataContext
import com.lanzdev.core.facades.vkpost.converters.impl.DocAttachmentConverter
import com.lanzdev.core.facades.vkpost.converters.impl.PhotoAttachmentConverter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import com.vk.api.sdk.objects.wall.WallpostAttachment
import com.vk.api.sdk.objects.wall.WallpostAttachmentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockitoExtension::class)
internal class AttachmentDataContextTest {

    private val docAttachmentConverter: DocAttachmentConverter = mock()
    private val photoAttachmentConverter: PhotoAttachmentConverter = mock()
    private val wallpostAttachment: WallpostAttachment = mock()
    private val wallpostAttachmentType: WallpostAttachmentType = mock()

    private val converters = mapOf(
            CustomWallpostAttachmentType.DOC.value to docAttachmentConverter,
            CustomWallpostAttachmentType.PHOTO.value to photoAttachmentConverter
    )
    private val context = AttachmentDataContext(converters)

    @BeforeEach
    fun `set up`() {
        reset(docAttachmentConverter, photoAttachmentConverter, wallpostAttachment, wallpostAttachmentType)
        whenever(wallpostAttachment.type).thenReturn(wallpostAttachmentType)
    }

    @Test
    fun `should return DocAttachmentConverter when wallpostAttachmentType is DOC`() {
        whenever(wallpostAttachmentType.value).thenReturn(CustomWallpostAttachmentType.DOC.value)
        val actualConverter = context.getConverterFor(wallpostAttachment)
        assertThat(actualConverter).isInstanceOf(DocAttachmentConverter::class.java)
    }

    @Test
    fun `should return PhotoAttachmentConverter when wallpostAttachmentType is PHOTO`() {
        whenever(wallpostAttachmentType.value).thenReturn(CustomWallpostAttachmentType.PHOTO.value)
        val actualConverter = context.getConverterFor(wallpostAttachment)
        assertThat(actualConverter).isInstanceOf(PhotoAttachmentConverter::class.java)
    }

    @Test
    fun `should return null when wallpostAttachmentType is not in (PHOTO, DOC)`() {
        whenever(wallpostAttachmentType.value).thenReturn(null)
        val actualConverter = context.getConverterFor(wallpostAttachment)
        assertThat(actualConverter).isNull()
    }
}