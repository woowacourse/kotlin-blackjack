fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class LanguageBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Map<String, Int> = languages
}

class SkillBuilder {
    private val skills: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        skills.add("soft: $value")
    }

    fun hard(value: String) {
        skills.add("hard: $value")
    }

    fun build(): List<String> = skills
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills: MutableList<String> = mutableListOf()
    private lateinit var languages: Map<String, Int>

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills.addAll(SkillBuilder().apply(block).build())
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills.toList(), languages)
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<String>,
    val languages: Map<String, Int>,
)
