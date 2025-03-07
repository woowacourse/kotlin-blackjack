package dsl

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills: MutableList<Skill> = mutableListOf()
    private val languages: MutableMap<String, Int> = mutableMapOf()

    fun build(): Person {
        return Person(name, company, skills, languages)
    }

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: List<Skill>.() -> Unit) {
        skills.apply(block)
    }

    fun soft(description: String) {
        skills.add(Skill(Skill.Type.SOFT, description))
    }

    fun hard(description: String) {
        skills.add(Skill(Skill.Type.HARD, description))
    }

    fun languages(block: Map<String, Int>.() -> Unit) {
        languages.apply(block)
    }

    infix fun String.level(other: Int) {
        languages[this] = other
    }
}
