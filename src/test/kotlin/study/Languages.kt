package study

class Languages(val languageLevels: Map<String, Int>)

class LanguageBuilder {
    private val languageLevels = mutableMapOf<String, Int>()

    infix fun String.level(that: Int) {
        languageLevels[this] = that
    }

    fun build(): Languages {
        return Languages(languageLevels)
    }
}
