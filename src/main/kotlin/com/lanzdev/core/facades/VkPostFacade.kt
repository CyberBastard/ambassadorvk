package com.lanzdev.core.facades

import com.lanzdev.core.data.AttachmentData

interface VkPostFacade {

    fun selectLast(screenName: String): List<AttachmentData>

    fun selectItems(screenName: String, limit: Int): List<AttachmentData>
}