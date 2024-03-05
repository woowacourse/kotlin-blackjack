package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun name() {
        val person: Person =
            introduce {
                name("홍길동")
                company("네이버")
                skills {
                    soft("123")
                    soft("456")
                    hard("789")
                }
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        assertThat(
            person,
        ).isEqualTo(Person("홍길동", "네이버", Skills(listOf("123", "456", "789")), Languages(mapOf("Korean" to 5, "English" to 3))))
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}
