package com.lanzdev.converters

import com.lanzdev.core.data.PhotoAttachmentData
import com.vk.api.sdk.objects.wall.WallpostAttachment

class PhotoAttachmentConverter : AttachmentConverter<PhotoAttachmentData> {

    override fun convertToData(source: WallpostAttachment) = PhotoAttachmentData().also {
        with(it) {
            source.photo.id?.let { id -> it.id = id }
            source.photo.photo75?.let { photo75 -> it.photo75 = photo75 }
            source.photo.photo130?.let { photo130 -> it.photo130 = photo130 }
            source.photo.photo604?.let { photo604 -> it.photo604 = photo604 }
            source.photo.photo807?.let { photo807 -> it.photo807 = photo807 }
            source.photo.photo1280?.let { photo1280 -> it.photo1280 = photo1280 }
            source.photo.photo2560?.let { photo2560 -> it.photo2560 = photo2560 }
            source.photo.text?.let { text -> it.text = text }
            source.photo.date?.let { date -> it.date = date }
        }
    }
}