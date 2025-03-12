package study.dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["박재성", "제이슨"])
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
                name("박재성")
                company("우아한형제들")
            }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun fullDsl() {
        val person =
            introduce {
                name("박재성")
                company("우아한형제들")
                skills {
                    soft("크루원 놀리기")
                    hard("Kotlin")
                }
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }

        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
        assertThat(person.skills.softSkills).containsExactly("크루원 놀리기")
        assertThat(person.skills.hardSkills).containsExactly("Kotlin")
        assertThat(person.languages["Korean"]).isEqualTo(5)
        assertThat(person.languages["English"]).isEqualTo(3)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills = Skills()
    private val languages = mutableMapOf<String, Int>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: Skills.() -> Unit) {
        skills.apply(block)
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        languages.putAll(LanguageBuilder().apply(block).build())
    }

    fun build(): Person = Person(name, company, skills, languages)
}

class Skills {
    val softSkills = mutableListOf<String>()
    val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }
}

class LanguageBuilder {
    private val levels = mutableMapOf<String, Int>()

    infix fun String.level(value: Int) {
        levels[this] = value
    }

    fun build(): Map<String, Int> = levels
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: Map<String, Int>,
)
