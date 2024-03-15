package study

class LanguageBuilder {
    private val languageLevels = mutableListOf<Map<String, Int>>()

    infix fun String.level(value: Int) {
        val map = mapOf(this to value)
        this@LanguageBuilder.languageLevels.add(map)
    }

    fun build(): Languages {
        return Languages(languageLevels)
    }
}
