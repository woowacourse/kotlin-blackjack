package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitTest {
    @Test
    fun `수익금 객체는 수익금을 가진다`() {
        val profit = Profit(100_000.0)

        val actual = profit.value

        val expected = 100_000.0

        assertThat(actual).isEqualTo(expected)
    }
}
