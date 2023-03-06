package study

class Languages() {
    private val _languages = mutableListOf<Language>()
    val languages: List<Language> get() = _languages.toList()

    infix fun String.level(level: Int) {
        _languages.add(Language(this, level))
    }

    data class Language(val nation: String, val level: Int)
}
