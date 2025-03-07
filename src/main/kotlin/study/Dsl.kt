package study

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private var name: String = ""
    private var company: String? = null
    private val skills: MutableList<String> = mutableListOf()
    private val languages: MutableList<Language> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        val builder = SkillsBuilder().apply(block)
        skills.addAll(builder.build())
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        val builder = LanguagesBuilder().apply(block)
        languages.addAll(builder.languages)
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    private val skills = mutableListOf<String>()

    fun soft(value: String) {
        skills.add(value)
    }

    fun hard(value: String) {
        skills.add(value)
    }

    fun build(): List<String> = skills
}

class LanguagesBuilder {
    val languages = mutableListOf<Language>()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }
}


data class Language(val name: String, val level: Int)

data class Person(
    val name: String,
    val company: String?,
    val skills: List<String>,
    val languages: List<Language>
)

