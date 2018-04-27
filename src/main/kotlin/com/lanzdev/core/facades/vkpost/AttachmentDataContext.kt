@file:JvmName("AttachmentDataContext")

package com.lanzdev.core.facades.vkpost

import com.lanzdev.core.data.AttachmentData
import com.lanzdev.core.facades.vkpost.converters.AttachmentConverter
import com.vk.api.sdk.objects.wall.WallpostAttachment
import org.springframework.stereotype.Component
import javax.annotation.Resource

@Component
class AttachmentDataContext() {

    @Resource
    lateinit var converters: Map<String, AttachmentConverter<out AttachmentData>>

    fun getConverterFor(attachment: WallpostAttachment): AttachmentConverter<out AttachmentData>? {
        return converters.entries.stream()
                .filter { it.key == attachment.type.value }
                .map { it.value }
                .findAny().orElse(null)
    }
}