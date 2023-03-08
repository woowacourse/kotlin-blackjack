import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person = introduce {
            name("윤인선")
            company("우아한테크코스")
        }
        assertThat(person.name).isEqualTo("윤인선")
        assertThat(person.company).isEqualTo("우아한테크코스")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("윤인선")
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
        assertThat(person.name).isEqualTo("윤인선")
        assertThat(person.company).isEqualTo("우아한테크코스")
        person.skills?.let {
            assertThat(it.soft.contains("A passion for problem solving"))
            assertThat(it.soft.contains("Good communication skills"))
            assertThat(it.hard.contains("Kotlin"))
        }
        person.languages?.let {
            assertThat(it.languages["Korean"]).isEqualTo(5)
            assertThat(it.languages["English"]).isEqualTo(3)
        }
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    class SkillsBuilder {
        private val soft = mutableListOf<String>()
        private val hard = mutableListOf<String>()
        fun build() = Skills(soft.toList(), hard.toList())
        fun soft(skill: String) {
            soft.add(skill)
        }

        fun hard(skill: String) {
            hard.add(skill)
        }
    }

    class LanguagesBuilder {
        private val languages = mutableMapOf<String, Int>()
        fun build() = Languages(languages.toMap())
        infix fun String.level(level: Int) = languages.put(this, level)
    }

    fun skills(block: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(block).build()
    }

    fun languages(block: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(block).build()
    }
    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

data class Person(
    var name: String,
    var company: String,
    var skills: Skills?,
    val languages: Languages?
)
data class Skills(val soft: List<String>, val hard: List<String>)
data class Languages(val languages: Map<String, Int>)
