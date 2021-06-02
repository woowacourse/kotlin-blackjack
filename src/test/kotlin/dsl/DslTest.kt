package dsl

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    internal fun person() {
        val person = introduce {
            name("amazzi")
            company("woowa")
            skills {
                soft("passion")
                hard("kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.name).isEqualTo("amazzi")
        assertThat(person.company).isEqualTo("woowa")
        assertThat(person.skills).contains("passion", "kotlin")
        assertThat(person.languages).containsExactly(Pair("Korean", 5), Pair("English", 3))
    }
}
