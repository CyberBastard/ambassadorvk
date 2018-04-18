package com.lanzdev.core.data

import com.vk.api.sdk.objects.wall.PostType

data class WallPostData(
        val id: String,
        val postType: PostType,
        val text: String,
        val attachments: List<AttachmentData>
)