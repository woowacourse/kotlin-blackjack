package study

class LanguagesBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(that: Int) {
        languages[this] = that
    }

    fun build(): Languages = Languages(languages.toMap())
}
