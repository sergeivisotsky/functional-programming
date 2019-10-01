package org.sergei.functional.isogram

/**
 * @author Sergei Visotsky
 */
class Isogram {
    fun isIsogram(text: String): Boolean {
        return text
                .toLowerCase()
                .filter { it !in " -" }
                .run { toSet().size == length }
    }
}
