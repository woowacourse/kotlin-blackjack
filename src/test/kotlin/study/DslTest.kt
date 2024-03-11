package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun company() {
        val person: Person =
            introduce {
                name("빙티")
                company("구글")
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
        assertThat(person.name).isEqualTo("빙티")
        assertThat(person.company).isEqualTo("구글")
        assertThat(person.skills.soft).isEqualTo(listOf("A passion for problem solving", "Good communication skills"))
        assertThat(person.skills.hard).isEqualTo(listOf("Kotlin"))
        assertThat(person.language.value).isEqualTo(listOf("Korean" to 5, "English" to 3))
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class Person(val name: String, val company: String, val skills: Skill, val language: Language)

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skill: Skill
    private lateinit var language: Language

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        this.skill = SkillBuilder().apply(block).skillBuild()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.language = LanguageBuilder().apply(block).languageBuild()
    }

    fun build(): Person {
        return Person(name, company, skill, language)
    }
}

class Skill(val soft: List<String>, val hard: List<String>)

class SkillBuilder {
    private val soft = mutableListOf<String>()
    private val hard = mutableListOf<String>()

    fun soft(softMessage: String) {
        this.soft.add(softMessage)
    }

    fun hard(hardMessage: String) {
        this.hard.add(hardMessage)
    }

    fun skillBuild(): Skill {
        return Skill(soft, hard)
    }
}

class LanguageBuilder {
    private val tmp = mutableListOf<Pair<String, Int>>()

    infix fun String.level(other: Int) {
        tmp.add(Pair(this, other))
    }

    fun languageBuild(): Language {
        return Language(tmp)
    }
}

class Language(val value: List<Pair<String, Int>>)
