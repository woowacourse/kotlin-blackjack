package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun introduce() {
        val person: Person = introduce {
            name("황태준")
            company("황태준컴퍼니")
            skills {
                soft("problem solving")
                soft("Good communication")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.name).isEqualTo("황태준")
        assertThat(person.company).isEqualTo("황태준컴퍼니")
        assertThat(person.skill.softSkills).hasSize(2)
        assertThat(person.skill.softSkills[0]).isEqualTo("problem solving")
        assertThat(person.skill.softSkills[1]).isEqualTo("Good communication")
        assertThat(person.skill.hardSkills).hasSize(1)
        assertThat(person.skill.hardSkills[0]).isEqualTo("Kotlin")
        assertThat(person.languages.languages).hasSize(2)
        assertThat(person.languages.languages["Korean"]).isEqualTo(5)
        assertThat(person.languages.languages["English"]).isEqualTo(3)
    }
}

// buildString이랑 apply가 이런 느낌으로 선언되어 있구나
private fun introduce(block: PersonBuilder.() -> Unit): Person { // Person에 있는 메서드면 다 받겠다
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private var name: String = "익명"
    private var company: String = "백수"
    private var languages: Languages = Languages()
    private var skill: Skill = Skill()

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: Skill.() -> Unit) {
        val skill = Skill()
        skill.block()
        this.skill = skill
    }

    fun languages(block: Languages.() -> Unit) {
        val languages = Languages()
        languages.block()
        this.languages = languages
    }

    fun build(): Person {
        return Person(name, company, skill, languages)
    }

}

class Skill {
    val softSkills = mutableListOf<String>()
    val hardSkills = mutableListOf<String>()

    fun soft(value: String) {
        softSkills.add(value)
    }

    fun hard(value: String) {
        hardSkills.add(value)
    }
}

class Languages {
    val languages = HashMap<String, Int>()

    infix fun String.level(level: Int) {
        languages[this] = level
    }
}

class Person(val name: String, val company: String,  val skill: Skill, val languages: Languages)