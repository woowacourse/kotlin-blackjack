import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(block: () -> String) { name = block() }

    fun company(block: () -> String) { company = block() }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

class SkillsBuilder {
    private val soft = MutableList(0) { "" }
    private val hard = MutableList(0) { "" }
    fun soft(block: () -> String) = soft.add(block())

    fun hard(block: () -> String) = hard.add(block())

    fun build(): Skills = Skills(soft, hard)
}

class LanguagesBuilder {
    private val languages: MutableMap<String, Int> = mutableMapOf()

    fun build(): Languages = Languages(languages.toMap())

    infix fun String.level(grade: Int) {
        languages[this] = grade
    }
}

fun person(block: PersonBuilder.() -> Unit): Person {
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
    private val languages: Map<String, Int>,
) {
    operator fun get(key: String) = languages[key]
    fun size() = languages.size
}

class DSLTest {
    @Test
    fun company() {
        val person = person {
            name { "아크" }
            company { "우아한형제들" }
            skills {
                soft { "A passion for problem solving" }
                soft { "Good communication skills" }
                hard { "Kotlin" }
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertAll(
            { assertThat(person.name).isEqualTo("아크") },
            { assertThat(person.company).isEqualTo("우아한형제들") },
            { assertThat(person.skills.soft[0]).isEqualTo("A passion for problem solving") },
            { assertThat(person.skills.soft[1]).isEqualTo("Good communication skills") },
            { assertThat(person.skills.hard[0]).isEqualTo("Kotlin") },
            { assertThat(person.languages["Korean"]).isEqualTo(5) },
            { assertThat(person.languages["English"]).isEqualTo(3) },
            { assertThat(person.languages.size()).isEqualTo(2) },
        )
    }
}
