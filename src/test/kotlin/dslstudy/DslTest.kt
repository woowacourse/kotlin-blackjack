package dslstudy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person = introduce() {
            name("json")
        }
        assertThat(person.name).isEqualTo("json")
    }

    @Test
    fun company() {
        val person = introduce() {
            name("json")
            company("우형")
        }
        assertThat(person.name).isEqualTo("json")
        assertThat(person.company).isEqualTo("우형")
    }

    @Test
    fun skills() {
        val person = introduce() {
            name("json")
            company("우형")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.name).isEqualTo("json")
        assertThat(person.company).isEqualTo("우형")
        assertThat(person.skills).isEqualTo(
            Skills(
                listOf("A passion for problem solving", "Good communication skills"),
                "Kotlin",
            ),
        )
    }

    @Test
    fun language() {
        val person = introduce() {
            name("json")
            company("우형")
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
        assertThat(person.name).isEqualTo("json")
        assertThat(person.company).isEqualTo("우형")
        assertThat(person.skills).isEqualTo(
            Skills(
                listOf("A passion for problem solving", "Good communication skills"),
                "Kotlin",
            ),
        )
        assertThat(person.language).isEqualTo(
            mapOf(
                "Korean" to 5,
                "English" to 3,
            ),
        )
    }
}

fun introduce(block: Person.() -> Unit): Person {
    val person = Person()
    person.block()
    return person
}

class Person() {
    var name: String = ""
    var company: String = ""
    lateinit var skills: Skills
    var language: Map<String, Int> = mapOf()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(setSkills: Skills.() -> Skills) {
        this.skills = Skills().setSkills()
    }

    fun languages(block3: () -> Unit) {
        block3()
    }

    infix fun String.level(value: Int) {
        language += Pair(this, value)
    }
}

data class Skills(
    var soft: List<String> = listOf(),
    var hard: String = "",
) {

    fun soft(soft: String): Skills {
        this.soft = this.soft + soft
        return this
    }

    fun hard(hard: String): Skills {
        this.hard = this.hard + hard
        return this
    }
}
