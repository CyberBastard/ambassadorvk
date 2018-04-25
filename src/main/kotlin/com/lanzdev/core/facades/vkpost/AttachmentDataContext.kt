@file:JvmName("AttachmentDataContext")

package com.lanzdev.core.facades.vkpost

import com.lanzdev.core.data.AttachmentData
import com.lanzdev.core.facades.vkpost.converters.AttachmentConverter
import com.vk.api.sdk.objects.wall.WallpostAttachment

class AttachmentDataContext(private val converters: Map<String, AttachmentConverter<out AttachmentData>>) {

    fun getConverterFor(attachment: WallpostAttachment): AttachmentConverter<out AttachmentData>? {
        return converters.entries.stream()
                .filter { it.key == attachment.type.value }
                .map { it.value }
                .findAny().orElse(null)
    }
}