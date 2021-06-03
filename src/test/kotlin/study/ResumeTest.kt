package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import study.dsl.Resume

internal class ResumeTest {

    @Test
    internal fun introduce() {
        val introduce: Resume = Resume().introduce {
            name("김배럴")
            company("우테코코")
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
        assertThat(introduce.name).isEqualTo("김배럴")
        assertThat(introduce.company).isEqualTo("우테코코")
        assertThat(introduce.skills.softSkills).hasSize(2)
        assertThat(introduce.skills.softSkills[0]).isEqualTo("A passion for problem solving")
        assertThat(introduce.skills.softSkills[1]).isEqualTo("Good communication skills")
        assertThat(introduce.skills.hardSkills).hasSize(1)
        assertThat(introduce.skills.hardSkills[0]).isEqualTo("Kotlin")

        assertThat(introduce.languages.languages).hasSize(2)
        assertThat(introduce.languages.languages["Korean"]).isEqualTo(5)
        assertThat(introduce.languages.languages["English"]).isEqualTo(3)

    }
}