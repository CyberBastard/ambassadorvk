package com.lanzdev.converters

import com.lanzdev.core.data.DocAttachmentData
import com.vk.api.sdk.objects.wall.WallpostAttachment

class DocAttachmentConverter : AttachmentConverter<DocAttachmentData> {

    override fun convertToData(source: WallpostAttachment) = DocAttachmentData().also {
        with(it) {
            source.doc.id?.let { id -> it.id = id }
            source.doc.title?.let { title -> it.title = title }
            source.doc.size?.let { size -> it.size = size }
            source.doc.ext?.let { ext -> it.ext = ext }
            source.doc.url?.let { url -> it.url = url }
        }
    }
}