@file:JvmName("WallPostDataConverter")

package com.lanzdev.core.facades.vkpost.converters.impl

import com.lanzdev.core.data.WallPostData
import com.lanzdev.core.facades.Converter
import com.lanzdev.core.facades.vkpost.AttachmentDataContext
import com.vk.api.sdk.objects.wall.WallPostFull

class WallPostDataConverter : Converter<WallPostFull, WallPostData> {

    lateinit var attachmentDataContext: AttachmentDataContext

    override fun convert(source: WallPostFull) = WallPostData().also { data ->
        with(data) {
            source.id?.let { data.id = it }
            source.postType?.let { data.postType = it }
            source.text?.let { data.text = it }
            source.attachments?.forEach { attachment ->
                attachmentDataContext.getConverterFor(attachment)?.let { data.attachments.add(it.convert(attachment)) }
            }
        }
    }
}
