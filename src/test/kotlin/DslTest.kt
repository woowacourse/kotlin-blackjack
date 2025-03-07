import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ValueSource(strings = ["박재성", "제이슨"])
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
        val actual = Skill(listOf("A passion for problem solving", "Good communication skills"), listOf("Kotlin"))
        assertThat(person.skills).isEqualTo(actual)
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
        val actual = Languages(5, 3)
        assertThat(person.languages).isEqualTo(actual)
    }
}
