@file:JvmName("PhotoAttachmentConverter")

package com.lanzdev.core.facades.vkpost.converters.impl

import com.lanzdev.core.data.PhotoAttachmentData
import com.lanzdev.core.facades.vkpost.converters.AttachmentConverter
import com.vk.api.sdk.objects.wall.WallpostAttachment
import org.springframework.stereotype.Component

@Component
class PhotoAttachmentConverter : AttachmentConverter<PhotoAttachmentData> {

    override fun convert(source: WallpostAttachment) = PhotoAttachmentData().also { data ->
        with(data) {
            source.photo.id?.let { data.id = it }
            source.photo.photo75?.let { data.photo75 = it }
            source.photo.photo130?.let { data.photo130 = it }
            source.photo.photo604?.let { data.photo604 = it }
            source.photo.photo807?.let { data.photo807 = it }
            source.photo.photo1280?.let { data.photo1280 = it }
            source.photo.photo2560?.let { data.photo2560 = it }
            source.photo.text?.let { data.text = it }
            source.photo.date?.let { data.date = it }
        }
    }
}