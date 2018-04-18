package com.lanzdev.core.facades.impl

import com.lanzdev.AttachmentDataContext
import com.lanzdev.core.data.AttachmentData
import com.lanzdev.core.facades.VkPostFacade
import com.lanzdev.core.services.VkPostService
import com.vk.api.sdk.objects.wall.WallPostFull

/**
 * Transforms attachments passed inside [WallPostFull] to [List] of [AttachmentData]
 */
class DefaultVkPostFacade : VkPostFacade {

    lateinit var defaultVkPostService: VkPostService
    lateinit var attachmentDataContext: AttachmentDataContext

    override fun selectLast(screenName: String): List<AttachmentData> {
        val lastPost = defaultVkPostService.selectLast(screenName)
        val attachments = mutableListOf<AttachmentData>()
        lastPost.attachments?.forEach { attachmentDataContext.convertToData(it)?.let { attachments.add(it) } }
        return attachments
    }

    override fun selectItems(screenName: String, limit: Int): List<AttachmentData> {
        val items = defaultVkPostService.selectItems(screenName, limit)
        val attachments = mutableListOf<AttachmentData>()
        items.forEach { it.attachments?.forEach { attachmentDataContext.convertToData(it)?.let { attachments.add(it) } } }
        return attachments
    }
}