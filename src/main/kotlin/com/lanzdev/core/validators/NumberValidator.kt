@file:JvmName("NumberValidator")

package com.lanzdev.core.validators

import org.springframework.stereotype.Component

@Component
class NumberValidator {

    fun validateInRange(input: Int, minValue: Int, maxValue: Int, fieldName: String) {
        if (input < minValue) {
            throw IllegalArgumentException("$fieldName should be bigger than $minValue, was $input")
        }
        if (input > maxValue) {
            throw IllegalArgumentException("$fieldName should be smaller than $maxValue, was $input")
        }
    }
}