@file:JvmName("AppConfig")

package com.lanzdev.config

import com.lanzdev.core.CustomWallpostAttachmentType
import com.lanzdev.core.facades.vkpost.converters.AttachmentConverter
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream
import java.util.*

@Configuration
@ComponentScan("com.lanzdev")
open class AppBeanFactoryAwareConfig : BeanFactoryAware {

    private lateinit var beanFactory: BeanFactory

    override fun setBeanFactory(beanFactory: BeanFactory?) {
        if (beanFactory != null) {
            this.beanFactory = beanFactory
        }
    }

    @Bean
    open fun botProps() = Properties().also { properties ->
        FileInputStream(this::class.java.classLoader.getResource("bot_real.properties").file).use {
            properties.load(it)
        }
    }

    @Bean
    open fun converters() = mapOf(
            CustomWallpostAttachmentType.DOC.value to beanFactory.getBean("docAttachmentConverter", AttachmentConverter::class.java),
            CustomWallpostAttachmentType.PHOTO.value to beanFactory.getBean("photoAttachmentConverter", AttachmentConverter::class.java)
    )
}
