import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun `introduce`() {
        val person = introduce {
            name("베르")
            company("우아한테크코스")
            skills {
                soft("질문하기")
                soft("상대방 배려하기")
                hard("Kotlin")
            }
            languages {
                "Korean" level 3
                "English" level 1
            }
        }
        assertThat(person.name).isEqualTo("베르")
        assertThat(person.company).isEqualTo("우아한테크코스")

        check(person.skills != null) { IllegalStateException() }
        assertThat(person.skills.soft).contains("질문하기")
        assertThat(person.skills.soft).contains("상대방 배려하기")
        assertThat(person.skills.hard).contains("Kotlin")

        check(person.languages != null) { IllegalStateException() }
        assertThat(person.languages.value).contains(Pair("Korean", 3))
        assertThat(person.languages.value).contains(Pair("English", 1))
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skills? = null
    private var languages: Languages? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(block: Skills.() -> Unit) {
        skills = Skills().apply(block)
    }

    fun languages(block: Languages.() -> Unit) {
        languages = Languages().apply(block)
    }

    fun build(): Person {
        return Person(
            name,
            company,
            skills,
            languages,
        )
    }
}

class Skills {
    val hard = mutableListOf<String>()
    val soft = mutableListOf<String>()

    fun hard(skill: String) {
        hard.add(skill)
    }

    fun soft(skill: String) {
        soft.add(skill)
    }
}

class Languages {
    val value = mutableListOf<Pair<String, Int>>()
    infix fun String.level(n: Int) = value.add(Pair(this, n))
}

data class Person(
    val name: String,
    val company: String?,
    val skills: Skills?,
    val languages: Languages?,
)
