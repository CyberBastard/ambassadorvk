package com.lanzdev

import com.lanzdev.core.facades.vkpost.VkPostFacade
import org.springframework.stereotype.Component

@Component
class BotRunner(private val vkPostFacade: VkPostFacade) {

    fun distribute() {
        println(vkPostFacade.selectItems("dadoem", 10))
    }
}
