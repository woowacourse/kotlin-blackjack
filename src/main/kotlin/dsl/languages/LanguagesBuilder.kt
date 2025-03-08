package dsl.languages

class LanguagesBuilder {
    private var language: Language? = null

    infix fun String.level(level: Int) {
        language = Language(this, level)
    }

    fun build() = language
}
