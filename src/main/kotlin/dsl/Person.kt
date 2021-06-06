package dsl

class Person {
    lateinit var name: String
        private set
    lateinit var company: String
        private set
    var skills: Skills? = null
        private set
    var languages: Languages? = null
        private set

    fun name(name: String): String {
        this.name = name
        return name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(function: Skills.() -> Unit) {
        this.skills = Skills().apply(function)
    }

    fun languages(function: Languages.() -> Unit) {
        this.languages = Languages().apply(function)
    }
}
