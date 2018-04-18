package com.lanzdev.core.services.impl

import com.lanzdev.core.dao.VkPostDao
import com.lanzdev.core.services.VkPostService
import com.lanzdev.core.validators.NumberValidator
import com.lanzdev.core.validators.StringValidator
import com.vk.api.sdk.objects.wall.WallPostFull

class DefaultVkPostService : VkPostService {

    lateinit var vkPostDao: VkPostDao
    lateinit var stringValidator: StringValidator
    lateinit var numberValidator: NumberValidator

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