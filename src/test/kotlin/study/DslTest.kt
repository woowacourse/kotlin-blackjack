package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun `코틀린 DSL 실습`() {
        val person: Person =
            introduce {
                name("레오")
                company("우아한 형제들")
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
        assertThat(person.name).isEqualTo("레오")
        assertThat(person.company).isEqualTo("우아한 형제들")
        assertThat(person.skills.softSkills).contains("A passion for problem solving", "Good communication skills")
        assertThat(person.skills.hardSkills).contains("Kotlin")
        assertThat(person.languages).containsKeys("Korean", "English")
        assertThat(person.languages["Korean"]).isEqualTo(5)
        assertThat(person.languages["English"]).isEqualTo(3)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()
    private val languages = mutableMapOf<String, Int>()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        SkillsBuilder().apply(block).also {
            softSkills.addAll(it.softSkills)
            hardSkills.addAll(it.hardSkills)
        }
    }

    fun languages(item: LanguageBuilder.() -> Unit) {
        LanguageBuilder().apply(item).also {
            languages.putAll(it.languages)
        }
    }

    fun build(): Person {
        return Person(name, company, Skills(softSkills, hardSkills), languages)
    }
}

class LanguageBuilder {
    val languages = mutableMapOf<String, Int>()

    infix fun String.level(other: Int) {
        languages[this] = other
    }
}

class SkillsBuilder {
    val softSkills = mutableListOf<String>()
    val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }
}

data class Person(val name: String, val company: String, val skills: Skills, val languages: Map<String, Int>)

data class Skills(val softSkills: List<String>, val hardSkills: List<String>)
