package dsl

class PersonBuilder {
    private var name: String? = null
    private var company: String? = null
    private val softSkills = mutableListOf<Skill>()
    private val hardSkills = mutableListOf<Skill>()
    private val languages: MutableList<Language> = mutableListOf()

    fun build(): Person {
        val name = requireNotNull(name) { "이름을 반드시 초기화 해야합니다." }
        return Person(name, company, softSkills + hardSkills, languages)
    }

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        val skills = SkillsBuilder().apply(block)
        softSkills.addAll(skills.softSkills)
        hardSkills.addAll(skills.hardSkills)
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages.addAll(LanguageBuilder().apply(block).build())
    }
}
