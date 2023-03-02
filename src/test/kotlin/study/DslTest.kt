package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun nameTest() {
        val person = introduce {
            name("빅스")
        }
        assertThat(person.name).isEqualTo("빅스")
    }

    @Test
    fun companyTest() {
        val person = introduce {
            name("빅스")
            company("우아한테크코스")
        }
        assertThat(person.name).isEqualTo("빅스")
        assertThat(person.company).isEqualTo("우아한테크코스")
    }

    @Test
    fun skillsTest() {
        val person = introduce {
            name("빅스")
            skills {
                soft("상대방의 의견 존중하기")
                soft("둥글게 말하기")
                hard("Kotlin")
            }
            languages {
                "Korean" level 3
                "English" level 1
            }
        }

        assertThat(person.name).isEqualTo("빅스")
        assertThat(person.company).isEqualTo(null)
        assertThat(person.skills?.skills?.size).isEqualTo(3)
        assertThat(person.languages?.languages?.size).isEqualTo(2)
    }
}
