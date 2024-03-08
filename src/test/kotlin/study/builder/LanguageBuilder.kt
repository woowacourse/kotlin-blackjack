package study.builder

import study.model.Language

class LanguageBuilder {
    private val languageLevels = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languageLevels[this] = level
    }

    fun build(): Language {
        return Language(languageLevels.toMap())
    }
}
