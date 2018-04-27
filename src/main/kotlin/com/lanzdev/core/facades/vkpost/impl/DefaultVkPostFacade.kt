@file:JvmName("DefaultVkPostFacade")

package com.lanzdev.core.facades.vkpost.impl

import com.lanzdev.core.data.AttachmentData
import com.lanzdev.core.data.WallPostData
import com.lanzdev.core.facades.vkpost.AttachmentDataContext
import com.lanzdev.core.facades.vkpost.VkPostFacade
import com.lanzdev.core.facades.vkpost.converters.impl.WallPostDataConverter
import com.lanzdev.core.services.VkPostService
import com.vk.api.sdk.objects.wall.WallPostFull
import org.springframework.stereotype.Component

/**
 * Transforms attachments passed inside [WallPostFull] to [List] of [AttachmentData]
 */
@Component
class DefaultVkPostFacade(
        private val vkPostService: VkPostService,
        private val attachmentDataContext: AttachmentDataContext,
        private val wallPostDataConverter: WallPostDataConverter
) : VkPostFacade {

    override fun selectLast(screenName: String): List<AttachmentData> = mutableListOf<AttachmentData>().also { attachments ->
        vkPostService.selectLast(screenName).attachments?.forEach { attachment ->
            attachmentDataContext.getConverterFor(attachment)?.let { attachments.add(it.convert(attachment)) }
        }
    }

    override fun selectItems(screenName: String, limit: Int): List<WallPostData> = mutableListOf<WallPostData>().also { wallPostsData ->
        vkPostService.selectItems(screenName, limit).forEach {
            wallPostsData.add(wallPostDataConverter.convert(it))
        }
    }
}
