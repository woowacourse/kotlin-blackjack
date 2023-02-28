package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person = introduce {
            name("스캇")
        }
        assertThat(person.name).isEqualTo("스캇")
    }

    @Test
    fun company() {
        val person = introduce {
            name("스캇")
            company("우아한테크코스")
        }
        assertThat(person.name).isEqualTo("스캇")
        assertThat(person.company).isEqualTo("우아한테크코스")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("스캇")
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
        assertThat(person.name).isEqualTo("스캇")
        assertThat(person.company).isEqualTo("우아한테크코스")
        assertThat(person.skills?.soft).containsExactly("A passion for problem solving", "Good communication skills")
        assertThat(person.skills?.hard).containsExactly("Kotlin")
        assertThat(person.languages?.languages).containsExactly("Korean" to 5, "English" to 3)
    }

    fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }

    class PersonBuilder {
        private lateinit var name: String
        private var company: String? = null
        private var skills: Skills? = null
        private var languages: Languages? = null
        fun name(name: String) {
            this.name = name
        }

        fun company(company: String) {
            this.company = company
        }

        fun skills(block: Skills.() -> Unit) {
            skills = Skills()
            skills?.apply(block)
        }

        fun languages(block: Languages.() -> Unit) {
            languages = Languages()
            languages?.apply(block)
        }

        fun build(): Person {
            return Person(name, company, skills, languages)
        }
    }

    data class Person(val name: String, val company: String?, val skills: Skills?, val languages: Languages?)

    class Skills {
        private val _soft = mutableListOf<String>()
        val soft
            get() = _soft.toList()
        private val _hard = mutableListOf<String>()
        val hard
            get() = _hard.toList()

        fun soft(skill: String) {
            _soft.add(skill)
        }

        fun hard(skill: String) {
            _hard.add(skill)
        }
    }

    class Languages {
        private val _languages = mutableListOf<Pair<String, Int>>()
        val languages
            get() = _languages.deepCopy()

        infix fun String.level(level: Int) {
            _languages.add(this to level)
        }

        private fun MutableList<Pair<String, Int>>.deepCopy(): List<Pair<String, Int>> {
            return this.map {
                it.copy()
            }
        }
    }
}
