package com.lanzdev.core.facades

import com.lanzdev.AttachmentDataContext
import com.lanzdev.MockitoExtension
import com.lanzdev.core.data.DocAttachmentData
import com.lanzdev.core.data.PhotoAttachmentData
import com.lanzdev.core.facades.impl.DefaultVkPostFacade
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
    private val context: AttachmentDataContext = mock()
    private val wallPostFull1: WallPostFull = mock()
    private val wallPostFull2: WallPostFull = mock()
    private val photoAttachment: WallpostAttachment = mock()
    private val photoAttachmentData: PhotoAttachmentData = mock()
    private val docAttachment: WallpostAttachment = mock()
    private val docAttachmentData: DocAttachmentData = mock()

    @InjectMocks
    private val facade = DefaultVkPostFacade()

    @BeforeEach
    fun `set up mocks`() {
        reset(service, context, wallPostFull1, wallPostFull2, photoAttachment, photoAttachmentData, docAttachment, docAttachmentData)

        whenever(wallPostFull1.attachments).thenReturn(listOf(photoAttachment))
        whenever(wallPostFull2.attachments).thenReturn(listOf(docAttachment))

        whenever(service.selectLast(screenName)).thenReturn(wallPostFull1)
        whenever(service.selectItems(screenName, limit)).thenReturn(listOf(wallPostFull1, wallPostFull2))

        whenever(photoAttachment.type).thenReturn(WallpostAttachmentType.PHOTO)
        whenever(docAttachment.type).thenReturn(WallpostAttachmentType.DOC)
        whenever(context.convertToData(photoAttachment)).thenReturn(photoAttachmentData)
        whenever(context.convertToData(docAttachment)).thenReturn(docAttachmentData)
    }

    @Nested
    inner class WhenSelectLast {
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
        @Test
        fun `should call select items on service`() {
            facade.selectItems(screenName, limit)
            verify(service).selectItems(screenName, limit)
        }

        @Test
        fun `and it has few should return list of attachments`() {
            val actual = facade.selectItems(screenName, limit)
            assertThat(actual).contains(photoAttachmentData, docAttachmentData)
        }
    }
}

