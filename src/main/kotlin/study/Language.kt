package study

data class Language(val languages: Map<String, Int>)

class LanguageBuilder {
    private val languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(value: Int) {
        languages[this] = value
    }

    fun build(): Language {
        return Language(languages)
    }
}
