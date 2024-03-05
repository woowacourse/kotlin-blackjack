package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["케이엠", "해음"])
    fun name(value: String) {
        val person: Person =
            introduce { // Person이라는 힌트가 생김
                name(value) // person의 name을 가리킴
            }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person: Person =
            introduce {
                name("홍길동")
                company("네이버")
            }

        assertThat(person.name).isEqualTo("홍길동")
        assertThat(person.company).isEqualTo("네이버")
    }

    @Test
    fun skills() {
        val person: Person =
            introduce {
                name("홍길동")
                company("네이버")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }
        assertThat(person.name).isEqualTo("홍길동")
        assertThat(person.company).isEqualTo("네이버")
        assertThat(person.skill).isEqualTo(
            Skill(
                soft =
                    listOf(
                        "A passion for problem solving",
                        "Good communication skills",
                    ),
                hard =
                    listOf(
                        "Kotlin",
                    ),
            ),
        )
    }

    /*
     * 컴파일 에러 2개 : Person, introduce
     */

    @Test
    fun languages() {
        val person: Person =
            introduce {
                name("홍길동")
                company("네이버")
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
        assertThat(person.name).isEqualTo("홍길동")
        assertThat(person.company).isEqualTo("네이버")
        assertThat(person.skill).isEqualTo(
            Skill(
                soft =
                    listOf(
                        "A passion for problem solving",
                        "Good communication skills",
                    ),
                hard =
                    listOf(
                        "Kotlin",
                    ),
            ),
        )
        assertThat(person.language).isEqualTo(Language(mapOf("Korean" to 5, "English" to 3)))
    }
}

data class Skill(
    val soft: List<String>,
    val hard: List<String>,
)

class SkillBuilder {
    private var soft: MutableList<String> = mutableListOf()
    private var hard: MutableList<String> = mutableListOf()

    fun soft(value: String) {
        this.soft.add(value)
    }

    fun hard(value: String) {
        this.hard.add(value)
    }

    fun build(): Skill {
        return Skill(soft, hard)
    }
}

data class Language(val languages: Map<String, Int>)

class LanguageBuilder {
    private val languages: MutableMap<String, Int> = mutableMapOf()

    infix fun String.level(value: Int) {
        languages[this] = value
    }

    fun build(): Language {
        return Language(languages)
    }
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null
    private var skills: Skill? = null
    private var languages: Language? = null

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(block: SkillBuilder.() -> Unit) {
        this.skills = SkillBuilder().apply(block).build()
    }

    fun languages(block: LanguageBuilder.() -> Unit) {
        this.languages = LanguageBuilder().apply(block).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class Person(
    val name: String,
    val company: String?,
    val skill: Skill?,
    val language: Language?,
)

fun introduce(block: PersonBuilder.() -> Unit): Person { // 수신객체 지정. Person만 해당 함수를 활용할 수 있도록 한다.
    return PersonBuilder().apply(block).build()
//    person.block() // 확장함수마냥 호출 가능해짐.
//    return person
}
