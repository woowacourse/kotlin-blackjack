package blackjack.model

import model.Profit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitTest {
    @Test
    fun `수익금 값을 가질 수 있다`() {
        val profit = Profit(1_000L)
        assertThat(profit).isEqualTo(Profit(1_000L))
    }
}
