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
    fun skill() {
        val person =
            introduce {
                name("박재성")
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.skills?.get(0)).isEqualTo(Skill("A passion for problem solving", "soft"))
        assertThat(person.skills?.get(1)).isEqualTo(Skill("Good communication skills", "soft"))
        assertThat(person.skills?.get(2)).isEqualTo(Skill("Kotlin", "hard"))
    }

    @Test
    fun language() {
        val person =
            introduce {
                name("박재성")
                language("Korean" level 5)
                language("English" level 3)
            }
        assertThat(person.name).isEqualTo("박재성")
    }
}

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

    fun soft(value: String) {
        skills.add(Skill(value, "soft"))
    }

    fun hard(value: String) {
        skills.add(Skill(value, "hard"))
    }

    fun language(value: Language) {
        languages.add(value)
    }

    fun build(): Person {
        return Person(name, company, skills.ifEmpty { null }, languages.ifEmpty { null })
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(
    val name: String,
    val company: String?,
    val skills: List<Skill>?,
    val languages: List<Language>?,
)

data class Skill(
    val name: String,
    val level: String,
)

data class Language(
    val name: String,
    val level: Int,
)

infix fun String.level(level: Int) = Language(this, level)
