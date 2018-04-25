@file:JvmName("StringValidator")

package com.lanzdev.core.validators

class StringValidator {

    fun validateEmptiness(input: String, fieldName: String) {
        if (input.isEmpty()) {
            throw IllegalArgumentException("$fieldName cannot be empty")
        }
    }
}