import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun person() {
        val person =
            introduce {
                name("예찬")
                company("Google")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
                languages {
                    "Korean" level 5
                    "English" level 3
                    "Japanese" level 1
                }
            }

        assertThat(person.name).isEqualTo("예찬")
        assertThat(person.company).isEqualTo("Google")
        assertThat(person.skills.softSkills).isEqualTo(
            listOf(
                "A passion for problem solving",
                "Good communication skills",
            ),
        )
        assertThat(person.skills.hardSkills).isEqualTo(listOf("Kotlin"))
        assertThat(person.languages["Korean"]).isEqualTo(5)
        assertThat(person.languages["English"]).isEqualTo(3)
        assertThat(person.languages["Japanese"]).isEqualTo(1)
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
