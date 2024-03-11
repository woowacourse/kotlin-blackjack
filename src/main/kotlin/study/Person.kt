package study

class Person(
    val name: String,
    val company: String?,
    val skill: Skill?,
    val language: Language?,
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skill? = null
    private var languages: Language? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        this.skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
