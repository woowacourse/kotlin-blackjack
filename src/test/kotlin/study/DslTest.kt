package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person = introduce {
            name("산군")
        }

        assertThat(person.name).isEqualTo("산군")
    }

    @Test
    fun company() {
        val person = introduce {
            name("산군")
            company("우아한테크코스")
        }

        assertThat(person.company).isEqualTo("우아한테크코스")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("산군")
            company("우아한테크코스")

            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.skills).hasSize(3)
    }

    @Test
    fun languages() {
        val person = introduce {
            name("산군")
            company("우아한테크코스")

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

        assertThat(person.name).isEqualTo("산군")
        assertThat(person.levels).hasSize(2)
    }
}

fun introduce(block: Person.() -> Unit) = Person().apply(block)

class Person {
    var company: String = ""
    var name: String = ""
    val skills: MutableList<String> = mutableListOf()
    val levels: MutableList<Pair<String, Int>> = mutableListOf()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: Skill.() -> Unit) = Skill().apply(block)
    fun languages(block: Language.() -> Unit) = Language().apply(block)

    inner class Skill {

        fun soft(s: String) {
            skills.add(s)
        }

        fun hard(s: String) {
            skills.add(s)
        }
    }

    inner class Language {
        infix fun String.level(level: Int) = levels.add(Pair(this, level))
    }
}
