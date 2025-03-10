package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NumberTest {
    @Test
    fun `숫자는 2부터 10까지 존재한다`() {
        val numberValues: List<Int> = Number.entries.map { number -> number.value }
        assertThat(numberValues).hasSameElementsAs(2..10)
    }

    @Test
    fun `숫자는 해당 숫자로 계산한다`() {
        assertThat(Number.TWO.value).isEqualTo(2)
    }
}
