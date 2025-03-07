package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {
    @ParameterizedTest
    @ValueSource(strings = ["김가현", "박지민"])
    fun `소개할 이름을 확인할 수 있다`(name: String) {
        val person = introduce {
            name(name)
        }
        assertThat(person.name).isEqualTo(name)
    }

    @Test
    fun `김가현의 소속은 우아한테크코스이다`() {
        val person = introduce {
            name("김가현")
            company("우아한테크코스")
        }
        assertThat(person.name).isEqualTo("김가현")
        assertThat(person.company).isEqualTo("우아한테크코스")
    }

    @Test
    fun `스킬들에 담긴 내용을 확인할 수 있다`() {
        val person = introduce {
            name("테스트")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        assertThat(person.skills).containsExactly(
            "A passion for problem solving",
            "Good communication skills",
            "Kotlin"
        )
    }

    @Test
    fun `사용할 수 있는 언어를 확인할 수 있다`() {
        val person = introduce {
            name("테스트")
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages).containsExactly(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}
