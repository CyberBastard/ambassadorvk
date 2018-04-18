package com.lanzdev.core.data

import com.lanzdev.core.WallpostAttachmentType

class DocAttachmentData(
        id: Int = -1,
        var title: String = "",
        var size: Int = -1,
        var ext: String = "",
        var url: String = ""
) : AttachmentData(id, WallpostAttachmentType.DOC)