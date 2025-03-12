
import dslstudy.Person
import dslstudy.PersonBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    private fun introduce(block: PersonBuilder.() -> Unit): Person = PersonBuilder().apply(block).build()

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
                company("우아한형제들")
                skills {
                    soft("Passion")
                    hard("Kotlin")
                }
            }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
        assertThat(person.skill?.softSkill?.get(0)).isEqualTo("Passion")
        assertThat(person.skill?.hardSkill?.get(0)).isEqualTo("Kotlin")
    }

    @Test
    fun all() {
        val person =
            introduce {
                name("박재성")
                company("우아한형제들")
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
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
        assertThat("Good communication skills").isIn(person.skill?.softSkill)
        assertThat(person.languages?.get("Korean")).isEqualTo(5)
    }
}
