package dslstudy

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skill: Skill? = null
    private var languages: Map<String, Int>? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        val skillsBuilder = SkillsBuilder().apply(block)
        this.skill = skillsBuilder.build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        val languagesBuilder = LanguagesBuilder().apply(block)
        this.languages = languagesBuilder.getLanguages()
    }

    fun build(): Person = Person(name, company, skill, languages)
}
