@file:JvmName("DefaultVkPostService")

package com.lanzdev.core.services.impl

import com.lanzdev.core.dao.VkPostDao
import com.lanzdev.core.services.VkPostService
import com.lanzdev.core.validators.NumberValidator
import com.lanzdev.core.validators.StringValidator
import com.vk.api.sdk.objects.wall.WallPostFull
import org.springframework.stereotype.Component

@Component
class DefaultVkPostService(
        private val vkPostDao: VkPostDao,
        private val stringValidator: StringValidator,
        private val numberValidator: NumberValidator
) : VkPostService {

    override fun selectLast(screenName: String): WallPostFull {
        stringValidator.validateEmptiness(screenName, DefaultVkPostService::selectLast.parameters[1].name!!)
        return vkPostDao.selectLast(screenName)
    }

    override fun selectItems(screenName: String, limit: Int): List<WallPostFull> {
        stringValidator.validateEmptiness(screenName, DefaultVkPostService::selectItems.parameters[1].name!!)
        numberValidator.validateInRange(limit, 1, Int.MAX_VALUE, DefaultVkPostService::selectItems.parameters[2].name!!)
        return vkPostDao.selectItems(screenName, limit)
    }
}