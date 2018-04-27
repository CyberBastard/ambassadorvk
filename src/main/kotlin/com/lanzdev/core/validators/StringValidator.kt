@file:JvmName("StringValidator")

package com.lanzdev.core.validators

import org.springframework.stereotype.Component

@Component
class StringValidator {

    fun validateEmptiness(input: String, fieldName: String) {
        if (input.isEmpty()) {
            throw IllegalArgumentException("$fieldName cannot be empty")
        }
    }
}