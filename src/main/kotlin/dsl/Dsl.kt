package dsl

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills: MutableList<Skill> = mutableListOf()
    private val languages: MutableList<Language> = mutableListOf()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) = skills.addAll(SkillsBuilder().apply(block).build())

    fun languages(block: LanguagesBuilder.() -> Unit) = languages.addAll(LanguagesBuilder().apply(block).build())

    fun build(): Person = Person(name, company, skills, languages)
}

class SkillsBuilder {
    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Skill(SkillType.SOFT, value))
    }

    fun hard(value: String) {
        skills.add(Skill(SkillType.HARD, value))
    }

    fun build(): List<Skill> = skills.toList()
}

class LanguagesBuilder {
    private val languages: MutableList<Language> = mutableListOf()

    infix fun String.level(other: Int) = languages.add(Language(this, other))

    fun build(): List<Language> = languages.toList()
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>,
    val languages: List<Language>,
)

data class Skill(
    val type: SkillType,
    val description: String,
)

data class Language(
    val name: String,
    val level: Int,
)

enum class SkillType { SOFT, HARD }

fun main() {
    val jason =
        introduce {
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

    println(jason)
}
