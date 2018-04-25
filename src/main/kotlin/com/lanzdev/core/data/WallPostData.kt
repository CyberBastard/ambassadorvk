@file:JvmName("WallPostData")

package com.lanzdev.core.data

import com.vk.api.sdk.objects.wall.PostType

data class WallPostData(
        var id: Int = -1,
        var postType: PostType = PostType.POST,
        var text: String = "",
        var attachments: MutableList<AttachmentData> = mutableListOf()
)
