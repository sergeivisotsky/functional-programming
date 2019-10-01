package org.sergei.functional.isogram

/**
 * @author Sergei Visotsky
 */
class WordCount {
    fun phrase(text: String): Map<String, Int> =
            text
                    .split(" ")
                    .groupBy { it }
                    .map { (key, value) -> key to value.size }
                    .toMap()
}
