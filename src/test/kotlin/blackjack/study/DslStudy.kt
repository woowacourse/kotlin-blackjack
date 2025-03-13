package blackjack.study

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
    fun skills() {
        val person =
            introduce {
                skills {
                    soft("soft skill")
                    hard("hard skill")
                }
            }
        assertThat(person.skills).isEqualTo(listOf("soft skill", "hard skill"))
    }

    @Test
    fun languages() {
        val person =
            introduce {
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        assertThat(person.languages).isEqualTo(setOf(Language("Korean", 5), Language("English", 3)))
        assertThat(person.languages.find { it.name == "Korean" }?.level).isEqualTo(5)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private var name: String? = null
    private var company: String? = null
    private var skills: List<String> = emptyList()
    private var languages: Set<Language> = emptySet()

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
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    private val skills: MutableList<String> = mutableListOf()

    fun soft(skill: String) {
        skills.add(skill)
    }

    fun hard(skill: String) {
        skills.add(skill)
    }

    fun build(): List<String> {
        return skills
    }
}

class LanguagesBuilder {
    private val languages: MutableSet<Language> = mutableSetOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): Set<Language> {
        return languages
    }
}

class Language(
    val name: String,
    val level: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Language

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

class Person(name: String?, val company: String?, val skills: List<String>, val languages: Set<Language>) {
    val name = name ?: "Unknown"
}
