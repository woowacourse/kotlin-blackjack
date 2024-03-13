package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import study.builder.PersonBuilder
import study.model.Person

class DslTest {
    @ValueSource(strings = ["윤성현", "악어"])
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
                name("악어")
                company("우아한형제들")
            }
        assertThat(person.name).isEqualTo("악어")
        assertThat(person.company).isEqualTo("우아한형제들")
    }

    @Test
    fun skillTest() {
        val person =
            introduce {
                name("악어")
                company("우아한형제들")
                skills {
                    soft("Documentation")
                    soft("Confident on any situation")
                    hard("Kotlin")
                    hard("Java")
                }
            }
        assertThat(person.skill.softSkills == mutableListOf("Documentation", "Confident on any situation"))
        assertThat(person.skill.hardSkills == mutableListOf("Kotlin", "Java"))
    }

    @Test
    fun languageTest() {
        val person =
            introduce {
                name("악어")
                company("우아한형제들")
                languages {
                    "Korean" level 5
                    "English" level 4
                    "Chinese" level 3
                }
            }
        assertThat(person.language.languages == mapOf("Korean" to 5, "English" to 4, "Chinese" to 3))
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
