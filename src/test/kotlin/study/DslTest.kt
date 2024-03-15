package study

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person: Person =
            introduce {
                name("채채랑벼리")
                company("배달의민족")
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
        Assertions.assertThat(person.name).isEqualTo("채채랑벼리")
        Assertions.assertThat(person.company).isEqualTo("배달의민족")
        Assertions.assertThat(person.skills.soft).contains("A passion for problem solving", "Good communication skills")
        Assertions.assertThat(person.skills.hard).contains("Kotlin")
        Assertions.assertThat(person.languages.languageLevels).contains(
            mapOf("Korean" to 5),
            mapOf("English" to 3),
        )
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
