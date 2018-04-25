@file:JvmName("DocAttachmentConverter")

package com.lanzdev.core.facades.vkpost.converters.impl

import com.lanzdev.core.data.DocAttachmentData
import com.lanzdev.core.facades.vkpost.converters.AttachmentConverter
import com.vk.api.sdk.objects.wall.WallpostAttachment

class DocAttachmentConverter : AttachmentConverter<DocAttachmentData> {

    override fun convert(source: WallpostAttachment) = DocAttachmentData().also {data ->
        with(data) {
            source.doc.id?.let { data.id = it }
            source.doc.title?.let { data.title = it }
            source.doc.size?.let { data.size = it }
            source.doc.ext?.let { data.ext = it }
            source.doc.url?.let { data.url = it }
        }
    }
}
