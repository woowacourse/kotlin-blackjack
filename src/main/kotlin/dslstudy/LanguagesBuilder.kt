package dslstudy

class LanguagesBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) = languages.put(this, level)

    fun getLanguages(): Map<String, Int> = languages
}
