package study

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: Skills.() -> Unit) {
        skills = Skills().apply(block)
    }

    fun languages(block: Languages.() -> Unit) {
        languages = Languages().apply(block)
    }

    fun build() = Person(name, company, skills, languages)
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,
)
