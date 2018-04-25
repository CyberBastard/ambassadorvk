@file:JvmName("PhotoAttachmentData")

package com.lanzdev.core.data

import com.lanzdev.core.CustomWallpostAttachmentType

class PhotoAttachmentData (
        id: Int = -1,
        var photo75: String = "",
        var photo130: String = "",
        var photo604: String = "",
        var photo807: String = "",
        var photo1280: String = "",
        var photo2560: String = "",
        var text: String = "",
        var date: Int = -1
) : AttachmentData(id, CustomWallpostAttachmentType.PHOTO) {
    override fun toString(): String {
        return """
id: $id
            photo75: $photo75
            photo130: $photo130
            photo604: $photo604
            photo807: $photo807
            photo1280: $photo1280
            photo2560: $photo2560
            text: $text
            date: $date
            """
    }
}