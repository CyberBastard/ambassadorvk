package com.lanzdev.core.dao

import com.vk.api.sdk.objects.wall.WallPostFull

interface VkPostDao {

    fun selectLast(screenName: String): WallPostFull

    fun selectItems(screenName: String, limit: Int): List<WallPostFull>
}