package study

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
