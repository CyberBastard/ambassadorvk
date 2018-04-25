@file:JvmName("AttachmentConverter")

package com.lanzdev.core.facades.vkpost.converters

import com.lanzdev.core.facades.Converter
import com.vk.api.sdk.objects.wall.WallpostAttachment

interface AttachmentConverter<T> : Converter<WallpostAttachment, T>