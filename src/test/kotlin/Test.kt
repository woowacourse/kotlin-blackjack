import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply { block() }.build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply { block() }.build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

class SkillsBuilder {
    val soft = MutableList(0) { "" }
    val hard = MutableList(0) { "" }
    fun soft(name: String) = soft.add(name)

    fun hard(name: String) = hard.add(name)

    fun build(): Skills = Skills(soft, hard)
}

class LanguagesBuilder {
    var korean = 0
    var english = 0

    fun build(): Languages = Languages(korean, english)

    infix fun String.level(grade: Int) {
        when (this) {
            "Korean" -> korean = grade
            "English" -> english = grade
        }
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    val personBuilder = PersonBuilder().apply(block)
    return personBuilder.build()
}

data class Person(
    var name: String,
    var company: String?,
    var skills: Skills,
    var languages: Languages,
)

data class Skills(
    val soft: List<String>,
    val hard: List<String>,
)

data class Languages(
    val korean: Int,
    val english: Int,
)

class Test {
    @Test
    fun company() {
        val person = introduce {
            name("로피")
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
        assertThat(person.name).isEqualTo("로피")
        assertThat(person.company).isEqualTo("우아한형제들")
        assertThat(person.skills.soft.size).isEqualTo(2)
        assertThat(person.skills.hard.size).isEqualTo(1)
        assertThat(person.languages.korean).isEqualTo(5)
        assertThat(person.languages.english).isEqualTo(3)
    }
}
