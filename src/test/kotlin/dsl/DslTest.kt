package dsl

import dsl.DslTest.Companion.ERROR_NAME_EMPTY
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private var name: String? = null
    private var company: String? = null
    private var skills: Skill? = null
    private var languages: List<Language> = emptyList()

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
        return Person(requireNotNull(name) { ERROR_NAME_EMPTY }, company, skills, languages)
    }
}

class LanguageBuilder {
    private val languages = mutableListOf<Language>()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): List<Language> = languages
}

class SkillBuilder {
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }

    fun build(): Skill {
        return Skill(softSkills, hardSkills)
    }
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skill?,
    val languages: List<Language>,
)

data class Language(val name: String, val level: Int)

data class Skill(val soft: List<String>, val hard: List<String>)

class DslTest {
    @ValueSource(strings = ["미플", "채넛"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person =
            introduce {
                name(value)
            }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("미플")
                company("우아한테크코스")
            }
        assertThat(person.name).isEqualTo("미플")
        assertThat(person.company).isEqualTo("우아한테크코스")
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("미플")
                skills {
                    soft("게임하기")
                    hard("kotlin")
                }
            }
        assertThat(person.skills!!.soft[0]).isEqualTo("게임하기")
        assertThat(person.skills.hard[0]).isEqualTo("kotlin")
    }

    @Test
    fun language() {
        val person =
            introduce {
                name("미플")
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        assertThat(person.languages[0].level).isEqualTo(5)
        assertThat(person.languages[1].level).isEqualTo(3)
    }

    companion object {
        const val ERROR_NAME_EMPTY = "name은 비어있으면 안 됩니다."
    }
}
