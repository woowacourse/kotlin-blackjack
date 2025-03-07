package kotlindsl

fun main() {
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
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skill? = null
    private var languages: Language? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillBuilder {
    private var soft: MutableList<String> = mutableListOf()
    private var hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skill {
        return Skill(soft.toList(), hard.toList())
    }
}

class LanguageBuilder {
    private val language = mutableMapOf<String, Int>()

    infix fun String.level(other: Int) {
        language[this] = other
    }

    fun build(): Language {
        return Language(language)
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skill? = Skill(listOf(), listOf()),
    val languages: Language? = Language(mapOf()),
)

data class Skill(val soft: List<String>, val hard: List<String>)

data class Language(val languages: Map<String, Int>)
