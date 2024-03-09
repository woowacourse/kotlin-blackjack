package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person: Person =
            introduce {
                name("케이엠")
            }
        assertThat(person.name).isEqualTo("케이엠")
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
