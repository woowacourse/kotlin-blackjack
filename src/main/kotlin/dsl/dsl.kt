package dsl

fun main() {
    val person = introduce {
        name("박재성")
        company("우아한형제들")
        skills {
            soft("A passion for problem solving")
            soft("Good communication skills")
            hard("Kotlin")
        }
        languages {
            "Korean" level 5
            "English" level 3
        }
    }
    println(person)
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: List<Language>
)

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills = Skills()
    private val languages = mutableListOf<Language>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages.addAll(LanguagesBuilder().apply(block).build())
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Skills(
    val softSkills: MutableList<String> = mutableListOf(),
    val hardSkills: MutableList<String> = mutableListOf()
)

class SkillsBuilder {
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}

data class Language(val name: String, val level: Int)

class LanguagesBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): List<Language> = languages
}
