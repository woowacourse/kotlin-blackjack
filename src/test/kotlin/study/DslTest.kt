package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun introduce() {
        val person = introduce {
            name("Corgan")
        }
        assertThat(person.name).isEqualTo("Corgan")
    }

    @Test
    fun company() {
        val person = introduce {
            name("Corgan")
            company("wooteco")
        }
        assertThat(person.company).isEqualTo("wooteco")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("Corgan")
            company("wooteco")
            skills {
                soft("sss")
                hard("")
            }
        }

        assertThat(person.skill?.softs?.get(0) ?: "").isEqualTo("sss")
    }

    @Test
    fun languages() {
        val person = introduce {
            name("Corgan")
            company("wooteco")
            skills {
                soft("sss")
                hard("")
            }
            languages {
                "Korean" level 5
                "English" level 2
            }
        }

        assertThat(person.language?.languages?.get("Korean") ?: -1).isEqualTo(5)
    }

    fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }

    class PersonBuilder {
        private lateinit var name: String
        private var company: String? = null
        private var skill: Skill? = null
        private var language: Language? = null

        fun name(name: String) {
            this.name = name
        }

        fun company(company: String) {
            this.company = company
        }

        fun skills(block: SkillBuilder.() -> Unit) {
            this.skill = SkillBuilder().apply(block).build()
        }

        fun languages(block: LanguageBuilder.() -> Unit) {
            this.language = LanguageBuilder().apply(block).build()
        }

        fun build(): Person {
            return Person(name, company, skill, language)
        }
    }

    class SkillBuilder {
        val softs: MutableList<String> = mutableListOf()
        val hards: MutableList<String> = mutableListOf()

        fun soft(name: String) {
            softs.add(name)
        }

        fun hard(name: String) {
            hards.add(name)
        }

        fun build(): Skill {
            return Skill(softs, hards)
        }
    }

    class LanguageBuilder {
        val languages: MutableMap<String, Int> = mutableMapOf()

        infix fun String.level(other: Int) = languages.put(this, other)

        fun build(): Language {
            return Language(languages)
        }
    }

    data class Person(val name: String, val company: String?, val skill: Skill?, val language: Language?)
    data class Skill(val softs: List<String>, val hards: List<String>)
    data class Language(val languages: Map<String, Int>)
}
