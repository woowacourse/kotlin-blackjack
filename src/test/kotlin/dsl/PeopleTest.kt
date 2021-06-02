package dsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PeopleTest {
    @Test
    fun name() {
        val peoeple : People = introduce {
            name("aaron")
            company("WoowaTechCourse")
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
        assertThat(peoeple.name).isEqualTo("aaron")
        assertThat(peoeple.company).isEqualTo("WoowaTechCourse")
        assertThat(peoeple.skills.soft).contains("Good communication skills", "A passion for problem solving")
        assertThat(peoeple.skills.hard).contains("Kotlin")
        assertThat(peoeple.languages.languages.map { it.first }).containsExactly("Korean", "English")
        assertThat(peoeple.languages.languages.map { it.second }).containsExactly(5, 3)
    }
}