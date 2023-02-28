package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun name() {
        val person = introduce {
            name("수달1")
        }
        assertThat(person.name).isEqualTo("수달1")
    }

    @Test
    fun company() {
        val person = introduce {
            company("우테코1")
        }
        assertThat(person.company).isEqualTo("우테코1")
    }

    @Test
    fun skills() {
        val person = introduce {
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.skills.soft).isEqualTo(listOf("A passion for problem solving", "Good communication skills"))
        assertThat(person.skills.hard).isEqualTo(listOf("Kotlin"))
    }

    @Test
    fun languages() {
        val person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages.value).isEqualTo(mutableListOf(Pair("Korean", 5), Pair("English", 3)))
    }
}

// Person.() => 아무 함수
// Person.(String) => String 인자를 받는 함수만
fun introduce(block: Person.() -> Unit): Person {
    return Person().apply(block)
}

class Person() {
    var name: String = ""
    var company: String = ""
    var skills: Skills = Skills()
    var languages: Languages = Languages()

    fun name(s: String) {
        name = s
    }

    fun company(s: String) {
        company = s
    }

    fun skills(block: Skills.() -> Unit) {
        skills.apply(block)
    }

    fun languages(block: Languages.() -> Unit) {
        languages.apply(block)
    }
}

class Skills() {
    var soft: MutableList<String> = mutableListOf()
    var hard: MutableList<String> = mutableListOf()

    fun soft(s: String) {
        soft.add(s)
    }

    fun hard(s: String) {
        hard.add(s)
    }
}

class Languages() {
    val value: MutableList<Pair<String, Int>> = mutableListOf()

    infix fun String.level(other: Int) {
        value.add(Pair(this, other))
    }
}
