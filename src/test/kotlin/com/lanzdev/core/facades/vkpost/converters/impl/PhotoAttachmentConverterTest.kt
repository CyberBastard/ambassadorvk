package com.lanzdev.core.facades.vkpost.converters.impl

import com.lanzdev.MockitoExtension
import com.lanzdev.core.data.PhotoAttachmentData
import com.lanzdev.core.facades.vkpost.converters.impl.PhotoAttachmentConverter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import com.vk.api.sdk.objects.photos.Photo
import com.vk.api.sdk.objects.wall.WallpostAttachment
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockitoExtension::class)
internal class PhotoAttachmentConverterTest {

    private val testData: PhotoAttachmentData = PhotoAttachmentData(1,
            "PHOTO75", "PHOTO130", "PHOTO604", "PHOTO807", "PHOTO1280", "PHOTO2560",
            "TEXT", 2)

    private val wallpostAttachment: WallpostAttachment = mock()
    private val photo: Photo = mock()

    private val photoAttachmentConverter = PhotoAttachmentConverter()

    @BeforeEach
    fun `set up`() {
        reset(wallpostAttachment, photo)
        whenever(wallpostAttachment.photo).thenReturn(photo)
    }

    @Test
    fun `should populate id`() {
        whenever(photo.id).thenReturn(testData.id)
        val actual = photoAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.id).isEqualTo(testData.id)
    }

    @Test
    fun `should populate photo75`() {
        whenever(photo.photo75).thenReturn(testData.photo75)
        val actual = photoAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.photo75).isEqualTo(testData.photo75)
    }

    @Test
    fun `should populate photo130`() {
        whenever(photo.photo130).thenReturn(testData.photo130)
        val actual = photoAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.photo130).isEqualTo(testData.photo130)
    }

    @Test
    fun `should populate photo604`() {
        whenever(photo.photo604).thenReturn(testData.photo604)
        val actual = photoAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.photo604).isEqualTo(testData.photo604)
    }

    @Test
    fun `should populate photo807`() {
        whenever(photo.photo807).thenReturn(testData.photo807)
        val actual = photoAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.photo807).isEqualTo(testData.photo807)
    }

    @Test
    fun `should populate photo1280`() {
        whenever(photo.photo1280).thenReturn(testData.photo1280)
        val actual = photoAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.photo1280).isEqualTo(testData.photo1280)
    }

    @Test
    fun `should populate photo2560`() {
        whenever(photo.photo2560).thenReturn(testData.photo2560)
        val actual = photoAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.photo2560).isEqualTo(testData.photo2560)
    }

    @Test
    fun `should populate text`() {
        whenever(photo.text).thenReturn(testData.text)
        val actual = photoAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.text).isEqualTo(testData.text)
    }

    @Test
    fun `should populate date`() {
        whenever(photo.date).thenReturn(testData.date)
        val actual = photoAttachmentConverter.convert(wallpostAttachment)
        assertThat(actual.date).isEqualTo(testData.date)
    }
}