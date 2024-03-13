package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class AllTest {
    @Test
    fun `test`() {
        val a = listOf(1, 2, 3)
        assertAll(
            { assertThat(a.all { it < 4 }).isTrue() },
            { assertThat(a.any { it < 3 }).isTrue() },
        )
    }
}
