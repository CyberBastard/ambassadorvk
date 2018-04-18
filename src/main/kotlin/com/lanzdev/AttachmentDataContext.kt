package com.lanzdev

import com.lanzdev.converters.AttachmentConverter
import com.lanzdev.converters.DocAttachmentConverter
import com.lanzdev.converters.PhotoAttachmentConverter
import com.lanzdev.core.WallpostAttachmentType
import com.lanzdev.core.data.AttachmentData
import com.vk.api.sdk.objects.wall.WallpostAttachment

class AttachmentDataContext {

    private val converters: Map<String, AttachmentConverter<out AttachmentData>> = mapOf(
            WallpostAttachmentType.DOC.value to DocAttachmentConverter(),
            WallpostAttachmentType.PHOTO.value to PhotoAttachmentConverter()
    )

    fun convertToData(attachment: WallpostAttachment): AttachmentData? {
        val converter = converters.entries.stream()
                .filter { it.key == attachment.type.value }
                .map { it.value }
                .findAny()
        return if (converter.isPresent) {
            converter.get().convertToData(attachment)
        } else {
            null
        }
    }
}