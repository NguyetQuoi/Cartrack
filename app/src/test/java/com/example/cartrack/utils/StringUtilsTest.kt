package com.example.cartrack.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import com.example.cartrack.util.StringUtils

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class StringUtilsTest {
    /**
     * Compare null text
     */
    @Test
    fun stringUtils_capitalize_null() {
        assertEquals("", StringUtils.capitalize(null))
    }

    /**
     * Compare empty text with null
     */
    @Test
    fun stringUtils_capitalize_empty() {
        assertEquals("", StringUtils.capitalize(null))
    }

    /**
     * Compare lower case text
     */
    @Test
    fun stringUtils_capitalize_lowercase() {
        assertEquals("Abc", StringUtils.capitalize("abc"))
    }

    /**
     * Compare upper case text
     */
    @Test
    fun stringUtils_capitalize_uppercase() {
        assertEquals("ABC", StringUtils.capitalize("ABC"))
    }
}
