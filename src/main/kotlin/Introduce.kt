fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skill? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit)  {
        languages = LanguagesBuilder().apply(block).build()
    }
}

class SkillsBuilder {
    private var soft: MutableList<String> = mutableListOf()
    private var hard: MutableList<String> = mutableListOf()

    fun soft(softSkill: String)  {
        soft.add(softSkill)
    }

    fun hard(hardSkill: String)  {
        hard.add(hardSkill)
    }

    fun build(): Skill  {
        return Skill(soft, hard)
    }
}

class LanguagesBuilder {
    private var korean: Int = 0
    private var english: Int = 0

    infix fun String.level(value: Int) {
        when (this) {
            "Korean" -> korean = value
            "English" -> english = value
            else -> null
        }
    }

    fun build(): Languages  {
        return Languages(korean, english)
    }
}

data class Person(val name: String, val company: String?, val skills: Skill?, val languages: Languages?)

data class Skill(val soft: List<String>, val hard: List<String>)

data class Languages(val korean: Int, val english: Int)
