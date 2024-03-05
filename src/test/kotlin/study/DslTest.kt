package study

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages,
) {
    class Builder {
        private var name = ""
        private var company = ""
        private var skills: Skills = Skills()
        private var languages: Languages = Languages()

        fun name(name: String): Builder = run { this.name = name; this }
        fun company(company: String): Builder = run { this.company = company; this }
        fun skills(action: Skills.Builder.() -> Unit): Builder = run {
            Skills.Builder().apply(action).build(); return this
        }

        fun languages(action: Languages.Builder.() -> Unit): Builder = run {
            Languages.Builder().apply(action).build(); return this
        }

        fun build(): Person = Person(name, company, skills, languages)
    }
}

data class Skills(val softs: List<Skill> = emptyList(), val hards: List<Skill> = emptyList()) {
    class Builder {
        private var softs: List<Skill> = emptyList()
        private var hards: List<Skill> = emptyList()

        fun soft(skill: String) = run { this.softs += Skill(skill) }
        fun hard(skill: String) = run { this.hards += Skill(skill) }

        fun build(): Skills = Skills(softs, hards)
    }
}

data class Languages(val languages: List<Language> = emptyList()) {
    class Builder {
        private var languages: List<Language> = emptyList()

        infix fun String.level(amount: Int) = run { languages += Language(this@level, amount) }

        fun build(): Languages = Languages(languages)
    }
}

data class Language(val name: String, val level: Int)

data class Skill(val name: String)

fun introduce(action: Person.Builder.() -> Unit): Person {
    return Person.Builder().apply(action).build()
}

class DslTest {
    @Test
    fun `test`() {
        // given

        val person: Person = introduce {
            name("박재성")
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

        assertThat(person.name).isEqualTo("오둥이")
        assertThat(person.company).isEqualTo("우아한 테크 코스")
    }
}
