package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitsTest {
    private val profits =
        Profits(
            listOf(Profit("동전", 1000.0), Profit("도전", -500.0)),
        )

    @Test
    fun `수익만큼 손실로 계산한다`() {
        val actual = Profit("딜러", -500.0)
        assertThat(profits.calculateTotalLosses("딜러")).isEqualTo(actual)
    }
}
