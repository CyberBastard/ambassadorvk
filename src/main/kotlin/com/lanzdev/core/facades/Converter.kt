@file:JvmName("Converter")

package com.lanzdev.core.facades

interface Converter<SOURCE, TARGET> {

    fun convert(source: SOURCE) : TARGET
}