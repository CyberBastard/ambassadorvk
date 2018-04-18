package com.lanzdev.converters

import com.vk.api.sdk.objects.wall.WallpostAttachment

interface AttachmentConverter<T> {
    fun convertToData(source: WallpostAttachment) : T
}