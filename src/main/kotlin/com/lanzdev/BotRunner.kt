@file:JvmName("BotRunner")

package com.lanzdev

import com.lanzdev.contexts.VkContext
import com.lanzdev.core.facades.vkpost.converters.impl.DocAttachmentConverter
import com.lanzdev.core.facades.vkpost.converters.impl.PhotoAttachmentConverter
import com.lanzdev.core.CustomWallpostAttachmentType
import com.lanzdev.core.dao.impl.DefaultVkPostDao
import com.lanzdev.core.facades.vkpost.AttachmentDataContext
import com.lanzdev.core.facades.vkpost.impl.DefaultVkPostFacade
import com.lanzdev.core.services.impl.DefaultVkPostService
import com.lanzdev.core.validators.NumberValidator
import com.lanzdev.core.validators.StringValidator
import java.io.FileInputStream
import java.util.*

class BotRunner {

    private var botProps = getBotProperties()
    private val vkContext = VkContext(botProps)

    private val converters = mapOf(
            CustomWallpostAttachmentType.DOC.value to DocAttachmentConverter(),
            CustomWallpostAttachmentType.PHOTO.value to PhotoAttachmentConverter()
    )


    private val attachmentDataContext = AttachmentDataContext(converters)

    private val vkPostDao = DefaultVkPostDao(vkContext)
    private val vkPostService = DefaultVkPostService().also {
        it.vkPostDao = vkPostDao
        it.stringValidator = StringValidator()
        it.numberValidator = NumberValidator()
    }
    private val vkPostFacade = DefaultVkPostFacade().also {
        it.defaultVkPostService = vkPostService
        it.attachmentDataContext = attachmentDataContext
    }

    private fun getBotProperties(): Properties {
        val botProps = Properties()
        FileInputStream(this::class.java.classLoader.getResource("bot.properties").file).use {
            botProps.load(it)
        }
        return botProps
    }

    fun distribute() {
        println(vkPostFacade.selectItems("dadoem", 10))
    }
}