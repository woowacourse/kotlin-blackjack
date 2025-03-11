package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

data class Person(val name: String, val affiliation: String?, val skills: Skills?, val languages: List<Languages>?)

class PersonBuilder {
    private lateinit var name: String
    private var affiliation: String? = null
    private var skills: Skills? = null
    private var languages: List<Languages>? = null

    fun name(value: String) {
        name = value
    }

    fun affiliation(value: String) {
        affiliation = value
    }

    fun skills(block: SkillsBuilder.() -> Unit): Skills? {
        skills = SkillsBuilder().apply(block).build()
        return skills
    }

    fun languages(block: LanguagesBuilder.() -> Unit): List<Languages>? {
        languages = LanguagesBuilder().apply(block).build()
        return languages
    }

    fun build(): Person {
        return Person(name, affiliation, skills, languages)
    }
}

data class Skills(val sort: List<String>, val hard: List<String>)

class SkillsBuilder {
    private val soft: MutableList<String> = mutableListOf()
    private val hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }

    fun build(): Skills? {
        return Skills(soft, hard)
    }
}

data class Languages(val name: String, val ability: Int)

class LanguagesBuilder {
    private val languagesInfo: MutableList<Languages> = mutableListOf()
    private lateinit var name: String
    private var ability: Int = 0

    infix fun String.level(value: Int): Languages {
        name = this
        ability = value
        val languages = Languages(name, ability)
        languagesInfo.add(languages)
        return languages
    }

    fun build(): List<Languages>? {
        return languagesInfo
    }
}

class DslTest {
    @Test
    fun `dsl 테스트`() {
        introduce {
            name("채넛")
            affiliation("우아한테크코스")
            skills {
                soft("객체지향에 대해 관심이 많아요")
                hard("코틀린과 자바")
            }
            languages {
                "Korean" level 5
                "Kotlin" level 3
                "English" level 2
            }
        }
    }

    @Test
    fun introduce() {
        val person =
            introduce {
                name("채넛")
                affiliation("우아한테크코스")
            }

        assertThat(person.name).isEqualTo("채넛")
        assertThat(person.affiliation).isEqualTo("우아한테크코스")
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("채넛")
                skills {
                    soft("객체지향에 대해 관심이 많아요")
                    hard("코틀린과 자바")
                }
            }

        assertThat(person.skills!!.sort[0]).isEqualTo("객체지향에 대해 관심이 많아요")
        assertThat(person.skills.hard[0]).isEqualTo("코틀린과 자바")
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("채넛")
                languages {
                    "Korean" level 5
                    "Kotlin" level 3
                    "English" level 2
                }
            }

        assertThat(person.languages!![0].name).isEqualTo("Korean")
        assertThat(person.languages[0].ability).isEqualTo(5)
        assertThat(person.languages[1].name).isEqualTo("Kotlin")
        assertThat(person.languages[1].ability).isEqualTo(3)
        assertThat(person.languages[2].name).isEqualTo("English")
        assertThat(person.languages[2].ability).isEqualTo(2)
    }
}
