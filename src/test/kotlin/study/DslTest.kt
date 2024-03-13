package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["꼬상", "누누"])
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
        val personNunu =
            introduce {
                name("누누")
                company("우아한형제들")
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
        assertThat(personNunu.name).isEqualTo("누누")
        assertThat(personNunu.company).isEqualTo("우아한형제들")
        assertThat(personNunu.skills.softSkills).isEqualTo(
            listOf(
                "A passion for problem solving",
                "Good communication skills",
            ),
        )
        assertThat(personNunu.skills.hardSkills).isEqualTo(listOf("Kotlin"))
        assertThat(personNunu.languages["Korean"]).isEqualTo(5)
        assertThat(personNunu.languages["English"]).isEqualTo(3)

        val personKkosang =
            introduce {
                name("꼬상")
                company("우아한형제들")
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
        assertThat(personKkosang.name).isEqualTo("꼬상")
        assertThat(personKkosang.company).isEqualTo("우아한형제들")
        assertThat(personKkosang.skills.softSkills).isEqualTo(
            listOf(
                "A passion for problem solving",
                "Good communication skills",
            ),
        )
        assertThat(personKkosang.skills.hardSkills).isEqualTo(listOf("Kotlin"))
        assertThat(personKkosang.languages["Korean"]).isEqualTo(5)
        assertThat(personKkosang.languages["English"]).isEqualTo(3)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()
    private val languages = mutableMapOf<String, Int>()

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        val skillsBuilder = SkillsBuilder().apply(block)
        softSkills.addAll(skillsBuilder.build().softSkills)
        hardSkills.addAll(skillsBuilder.build().hardSkills)
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        val languagesBuilder = LanguagesBuilder().apply(block)
        languages.putAll(languagesBuilder.build().languages)
    }

    fun build(): Person {
        return Person(name, company, Skills(softSkills, hardSkills), languages)
    }
}

data class Person(val name: String, val company: String?, val skills: Skills, val languages: Map<String, Int>)

class SkillsBuilder {
    private val softSkills = mutableListOf<String>()
    private val hardSkills = mutableListOf<String>()

    fun soft(skill: String) {
        softSkills.add(skill)
    }

    fun hard(skill: String) {
        hardSkills.add(skill)
    }

    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}

data class Skills(val softSkills: List<String>, val hardSkills: List<String>)

class LanguagesBuilder {
    private val languages = mutableMapOf<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }

    fun build(): Languages {
        return Languages(languages)
    }
}

data class Languages(val languages: Map<String, Int>)
