package com.lanzdev.core.facades.vkpost.converters.impl

import com.lanzdev.MockitoExtension
import com.lanzdev.core.data.PhotoAttachmentData
import com.lanzdev.core.data.WallPostData
import com.lanzdev.core.facades.vkpost.AttachmentDataContext
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import com.vk.api.sdk.objects.wall.PostType
import com.vk.api.sdk.objects.wall.WallPostFull
import com.vk.api.sdk.objects.wall.WallpostAttachment
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks

@ExtendWith(MockitoExtension::class)
internal class WallPostDataConverterTest {

    private val attachmentDataContext: AttachmentDataContext = mock()
    private val photoAttachmentConverter: PhotoAttachmentConverter = mock()
    private val photoAttachment: WallpostAttachment = mock()
    private val photoAttachmentData: PhotoAttachmentData = mock()
    private val wallPostFull: WallPostFull = mock()
    private val wallPostData: WallPostData = mock()

    private val testData = WallPostData(1, PostType.COPY, "TEXT", mutableListOf(photoAttachmentData))
    private val emptyData = WallPostData()

    @InjectMocks
    private lateinit var converter: WallPostDataConverter

    @BeforeEach
    fun `set up`() {
        reset(attachmentDataContext, photoAttachmentConverter, photoAttachmentData, wallPostFull, wallPostData)
        whenever(attachmentDataContext.getConverterFor(photoAttachment)).thenReturn(photoAttachmentConverter)
        whenever(photoAttachmentConverter.convert(photoAttachment)).thenReturn(photoAttachmentData)
    }

    @Test
    fun `should populate id`() {
        whenever(wallPostFull.id).thenReturn(testData.id)
        val actualData = converter.convert(wallPostFull)
        assertThat(actualData.id).isEqualTo(testData.id)
    }

    @Test
    fun `should populate postType`() {
        whenever(wallPostFull.postType).thenReturn(testData.postType)
        val actualData = converter.convert(wallPostFull)
        assertThat(actualData.postType).isEqualTo(testData.postType)
    }

    @Test
    fun `should populate text`() {
        whenever(wallPostFull.text).thenReturn(testData.text)
        val actualData = converter.convert(wallPostFull)
        assertThat(actualData.text).isEqualTo(testData.text)
    }

    @Test
    fun `should populate attachments`() {
        whenever(wallPostFull.attachments).thenReturn(mutableListOf(photoAttachment))
        val actualData = converter.convert(wallPostFull)
        assertThat(actualData.attachments).containsAll(testData.attachments)
    }

    @Test
    fun `should not populate id if null`() {
        whenever(wallPostFull.id).thenReturn(null)
        val actualData = converter.convert(wallPostFull)
        assertThat(actualData.id).isEqualTo(emptyData.id)
    }

    @Test
    fun `should not populate postType if null`() {
        whenever(wallPostFull.postType).thenReturn(null)
        val actualData = converter.convert(wallPostFull)
        assertThat(actualData.postType).isEqualTo(emptyData.postType)
    }

    @Test
    fun `should not populate text if null`() {
        whenever(wallPostFull.text).thenReturn(null)
        val actualData = converter.convert(wallPostFull)
        assertThat(actualData.text).isEqualTo(emptyData.text)
    }

    @Test
    fun `should not populate attachments if null`() {
        whenever(wallPostFull.attachments).thenReturn(null)
        val actualData = converter.convert(wallPostFull)
        assertThat(actualData.attachments).isEqualTo(emptyData.attachments)
    }
}