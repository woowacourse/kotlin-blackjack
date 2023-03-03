package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun languages() {
        val person = introduce {
            name("아크")
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
        assertThat(person.name).isEqualTo("아크")
        assertThat(person.company).isEqualTo("우아한형제들")
        assertThat(person.skills.soft.size).isEqualTo(2)
        assertThat(person.skills.hard.size).isEqualTo(1)
        assertThat(person.languages.languages.size).isEqualTo(2)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages,
)

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillBuilder {
    val soft = MutableList(0) { "" }
    val hard = MutableList(0) { "" }

    fun soft(name: String) {
        soft.add(name)
    }

    fun hard(name: String) {
        hard.add(name)
    }

    fun build(): Skills {
        return Skills(soft, hard)
    }
}

data class Skills(
    val soft: List<String>,
    val hard: List<String>,
)

data class Languages(
    val languages: Map<String, Int>,
)

class LanguagesBuilder {
    val languages = mutableMapOf<String, Int>()

    infix fun String.level(number: Int) {
        languages.put(this, number)
    }

    fun build(): Languages {
        return Languages(languages)
    }
}
