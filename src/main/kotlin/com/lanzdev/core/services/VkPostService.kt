@file:JvmName("VkPostService")

package com.lanzdev.core.services

import com.vk.api.sdk.objects.wall.WallPostFull

interface VkPostService {

    fun selectLast(screenName: String) : WallPostFull

    fun selectItems(screenName: String, limit: Int) : List<WallPostFull>
}