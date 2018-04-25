@file:JvmName("DefaultVkPostFacade")

package com.lanzdev.core.facades.vkpost.impl

import com.lanzdev.core.data.AttachmentData
import com.lanzdev.core.data.WallPostData
import com.lanzdev.core.facades.vkpost.AttachmentDataContext
import com.lanzdev.core.facades.vkpost.VkPostFacade
import com.lanzdev.core.facades.vkpost.converters.impl.WallPostDataConverter
import com.lanzdev.core.services.VkPostService
import com.vk.api.sdk.objects.wall.WallPostFull

/**
 * Transforms attachments passed inside [WallPostFull] to [List] of [AttachmentData]
 */
class DefaultVkPostFacade : VkPostFacade {

    lateinit var defaultVkPostService: VkPostService
    lateinit var attachmentDataContext: AttachmentDataContext
    lateinit var wallPostDataConverter: WallPostDataConverter

    override fun selectLast(screenName: String): List<AttachmentData> = mutableListOf<AttachmentData>().also { attachments ->
        defaultVkPostService.selectLast(screenName).attachments?.forEach { attachment ->
            attachmentDataContext.getConverterFor(attachment)?.let { attachments.add(it.convert(attachment)) }
        }
    }

    override fun selectItems(screenName: String, limit: Int): List<WallPostData> = mutableListOf<WallPostData>().also { wallPostsData ->
        defaultVkPostService.selectItems(screenName, limit).forEach {
            wallPostsData.add(wallPostDataConverter.convert(it))
        }
    }
}
