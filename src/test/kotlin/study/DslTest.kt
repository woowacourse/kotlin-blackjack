package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class DslTest {
    @Test
    fun languages() {
        val person: Person =
            introduce {
                name("올리브")
                company("우아한형제들")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("kotlin")
                }
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        assertThat(person.name).isEqualTo("올리브")
        assertThat(person.company).isEqualTo("우아한형제들")
        assertThat(person.skills.soft[0]).isEqualTo("A passion for problem solving")
        assertThat(person.skills.soft[1]).isEqualTo("Good communication skills")
        assertThat(person.skills.hard.first()).isEqualTo("kotlin")
        assertThat(person.languages.languageLevels["Korean"]).isEqualTo(5)
        assertThat(person.languages.languageLevels["English"]).isEqualTo(3)
    }
}
