import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person = introduce {
            name("글로")
        }
        assertThat(person.name).isEqualTo("글로")
    }

    @Test
    fun company() {
        val person = introduce {
            name("글로")
            company("우아한테크코스")
        }
        assertThat(person.name).isEqualTo("글로")
        assertThat(person.company).isEqualTo("우아한테크코스")
    }

    @Test
    fun skills() {
        val person = introduce {
            name("글로")
            company("우아한테크코스")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.name).isEqualTo("글로")
        assertThat(person.company).isEqualTo("우아한테크코스")
        assertThat(person.skills["soft"]).containsExactly("A passion for problem solving", "Good communication skills")
        assertThat(person.skills["hard"]).containsExactly("Kotlin")
    }

    @Test
    fun language() {
        val person = introduce {
            name("글로")
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
        assertThat(person.name).isEqualTo("글로")
        assertThat(person.company).isEqualTo("우아한테크코스")
        assertThat(person.skills["soft"]).containsExactly("A passion for problem solving", "Good communication skills")
        assertThat(person.skills["hard"]).containsExactly("Kotlin")
        assertThat(person.languages["Korean"]).isEqualTo(5)
        assertThat(person.languages["English"]).isEqualTo(3)
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    /*val person = Person()
    person.block()
    return person*/
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private val skills: Skills = Skills()
    private val languages: Languages = Languages()

    fun name(name: String) {
        this.name = name
    }

    fun company(company: String) {
        this.company = company
    }

    fun soft(skill: String) {
        skills.add("soft", skill)
    }

    fun hard(skill: String) {
        skills.add("hard", skill)
    }

    fun skills(block: PersonBuilder.() -> Unit) {
        block()
    }

    infix fun String.level(level: Int) {
        languages.level(this, level)
    }

    fun languages(block: PersonBuilder.() -> Unit) {
        block()
    }

    fun build(): Person = Person(name, company, skills, languages)
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills,
    val languages: Languages,
)

class Languages {
    private val languages: MutableMap<String, Int> = mutableMapOf()

    fun level(language: String, level: Int) {
        languages[language] = level
    }

    operator fun get(key: String): Int = languages[key] ?: 0
}

class Skills {
    private val skills: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun add(type: String, skill: String) {
        skills.getOrPut(type, ::mutableListOf).add(skill)
    }

    operator fun get(key: String): List<String> = skills[key]?.toList() ?: listOf()
}
