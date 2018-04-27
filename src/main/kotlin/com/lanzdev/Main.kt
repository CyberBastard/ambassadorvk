@file:JvmName("Main")

package com.lanzdev

import com.lanzdev.config.AppBeanFactoryAwareConfig
import org.springframework.context.annotation.AnnotationConfigApplicationContext

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx = AnnotationConfigApplicationContext(AppBeanFactoryAwareConfig::class.java)
        ctx.getBean(BotRunner::class.java).distribute()
    }
}