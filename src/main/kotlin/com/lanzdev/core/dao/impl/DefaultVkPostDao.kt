@file:JvmName("DefaultVkPostDao")

package com.lanzdev.core.dao.impl

import com.lanzdev.contexts.VkContext
import com.lanzdev.core.dao.VkPostDao
import com.vk.api.sdk.objects.wall.WallPostFull
import com.vk.api.sdk.queries.wall.WallGetFilter
import org.springframework.stereotype.Component

@Component
class DefaultVkPostDao(private val vkContext: VkContext) : VkPostDao {

    override fun selectLast(screenName: String): WallPostFull {
        return vkContext.vk.wall().get(vkContext.actor)
                .filter(WallGetFilter.OWNER)
                .domain(screenName)
                .execute()
                .items[0]
    }

    override fun selectItems(screenName: String, limit: Int): List<WallPostFull> {
        return vkContext.vk.wall().get(vkContext.actor)
                .filter(WallGetFilter.OWNER)
                .domain(screenName)
                .execute()
                .items
                .take(limit)
    }
}