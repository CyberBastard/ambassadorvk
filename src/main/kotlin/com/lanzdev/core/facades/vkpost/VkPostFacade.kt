@file:JvmName("VkPostFacade")

package com.lanzdev.core.facades.vkpost

import com.lanzdev.core.data.AttachmentData
import com.lanzdev.core.data.WallPostData

interface VkPostFacade {

    fun selectLast(screenName: String): List<AttachmentData>

    fun selectItems(screenName: String, limit: Int): List<WallPostData>
}