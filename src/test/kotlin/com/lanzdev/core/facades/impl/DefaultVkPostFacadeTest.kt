package com.lanzdev.core.facades.impl

import com.lanzdev.MockitoExtension
import com.lanzdev.core.data.DocAttachmentData
import com.lanzdev.core.data.PhotoAttachmentData
import com.lanzdev.core.data.WallPostData
import com.lanzdev.core.facades.vkpost.AttachmentDataContext
import com.lanzdev.core.facades.vkpost.converters.impl.DocAttachmentConverter
import com.lanzdev.core.facades.vkpost.converters.impl.PhotoAttachmentConverter
import com.lanzdev.core.facades.vkpost.converters.impl.WallPostDataConverter
import com.lanzdev.core.facades.vkpost.impl.DefaultVkPostFacade
import com.lanzdev.core.services.VkPostService
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.vk.api.sdk.objects.wall.WallPostFull
import com.vk.api.sdk.objects.wall.WallpostAttachment
import com.vk.api.sdk.objects.wall.WallpostAttachmentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks

@ExtendWith(MockitoExtension::class)
internal class DefaultVkPostFacadeTest {

    val screenName = "screenName"
    val limit = 5

    private val service: VkPostService = mock()
    private val attachmentDataContext: AttachmentDataContext = mock()
    private val wallPostFull1: WallPostFull = mock()
    private val wallPostFull2: WallPostFull = mock()
    private val wallPostData1: WallPostData = mock()
    private val wallPostData2: WallPostData = mock()
    private val photoAttachment: WallpostAttachment = mock()
    private val photoAttachmentData: PhotoAttachmentData = mock()
    private val docAttachment: WallpostAttachment = mock()
    private val docAttachmentData: DocAttachmentData = mock()
    private val wallPostDataConverter: WallPostDataConverter = mock()
    private val photoAttachmentConverter: PhotoAttachmentConverter = mock()
    private val docAttachmentConverter: DocAttachmentConverter = mock()

    @InjectMocks
    private lateinit var facade: DefaultVkPostFacade

    @BeforeEach
    fun `set up mocks`() {
        reset(service, attachmentDataContext, wallPostFull1, wallPostFull2, photoAttachment, photoAttachmentData, docAttachment, docAttachmentData)

        whenever(service.selectLast(screenName)).thenReturn(wallPostFull1)
        whenever(service.selectItems(screenName, limit)).thenReturn(listOf(wallPostFull1, wallPostFull2))
    }

    @Nested
    inner class WhenSelectLast {

        @BeforeEach
        fun `set up mocks`() {
            whenever(wallPostFull1.attachments).thenReturn(listOf(photoAttachment))
            whenever(wallPostFull2.attachments).thenReturn(listOf(docAttachment))

            whenever(photoAttachmentConverter.convert(photoAttachment)).thenReturn(photoAttachmentData)
            whenever(docAttachmentConverter.convert(docAttachment)).thenReturn(docAttachmentData)

            whenever(attachmentDataContext.getConverterFor(photoAttachment)).thenReturn(photoAttachmentConverter)
            whenever(attachmentDataContext.getConverterFor(docAttachment)).thenReturn(docAttachmentConverter)

            whenever(photoAttachment.type).thenReturn(WallpostAttachmentType.PHOTO)
            whenever(docAttachment.type).thenReturn(WallpostAttachmentType.DOC)
        }

        @Test
        fun `should call select last on service`() {
            facade.selectLast(screenName)
            verify(service).selectLast(screenName)
        }

        @Test
        fun `and it has photo attachment should return photo attachment data`() {
            val actual = facade.selectLast(screenName)
            assertThat(listOf(photoAttachmentData)).isEqualTo(actual)
        }
    }

    @Nested
    inner class WhenSelectItems {

        @BeforeEach
        fun `set up mocks`() {
            whenever(wallPostData1.attachments).thenReturn(mutableListOf(photoAttachmentData))
            whenever(wallPostData2.attachments).thenReturn(mutableListOf(docAttachmentData))

            whenever(wallPostDataConverter.convert(wallPostFull1)).thenReturn(wallPostData1)
            whenever(wallPostDataConverter.convert(wallPostFull2)).thenReturn(wallPostData2)
        }

        @Test
        fun `should call select items on service`() {
            facade.selectItems(screenName, limit)
            verify(service).selectItems(screenName, limit)
        }

        @Test
        fun `and it has few should return list of attachments`() {
            val actualWallPostsData = facade.selectItems(screenName, limit)
            assertThat(actualWallPostsData).contains(wallPostData1, wallPostData2)
        }
    }
}

