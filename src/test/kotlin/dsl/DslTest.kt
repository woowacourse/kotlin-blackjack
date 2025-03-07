package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    fun introduce(block: PersonBuilder.() -> Unit): Person {
        return PersonBuilder().apply(block).build()
    }

    @ValueSource(strings = ["디랙", "제이슨"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person =
            introduce {
                name(value)
            }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person =
            introduce {
                name("박재성")
                company("우아한형제들")
            }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun skills() {
        val person =
            introduce {
                name("박재성")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.skills.contains(Skill(Skill.Type.SOFT, "A passion for problem solving"))).isTrue()
        assertThat(person.skills.contains(Skill(Skill.Type.SOFT, "Good communication skills"))).isTrue()
        assertThat(person.skills.contains(Skill(Skill.Type.HARD, "Kotlin"))).isTrue()
    }

    @Test
    fun languages() {
        val person =
            introduce {
                name("박재성")
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.languages["Korean"]).isEqualTo(5)
        assertThat(person.languages["English"]).isEqualTo(3)
    }
}
