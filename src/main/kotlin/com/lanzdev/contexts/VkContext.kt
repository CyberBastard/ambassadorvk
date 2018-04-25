@file:JvmName("VkContext")

package com.lanzdev.contexts

import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import java.util.*

class VkContext(botProps: Properties) {
    private val transportClient = HttpTransportClient()
    val vk: VkApiClient
    val actor: UserActor

    init {
        vk = VkApiClient(transportClient)
        actor = UserActor(Integer.parseInt(botProps.getProperty("vk.user.id")), botProps.getProperty("vk.actor"))
    }
}