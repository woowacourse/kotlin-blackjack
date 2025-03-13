package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BetAmountTest {
    private lateinit var betAmount: BetAmount

    @BeforeEach
    fun setUp() {
        betAmount = BetAmount(Money(1000))
    }

    @Test
    fun `승리 수익 금액을 계산한다`() {
        val actual = Profit(1000)
        assertThat(betAmount.calculate(VerdictResult.WIN)).isEqualTo(actual)
    }

    @Test
    fun `패배 수익 금액을 계산한다`() {
        val actual = Profit(-1000)
        assertThat(betAmount.calculate(VerdictResult.LOSE)).isEqualTo(actual)
    }

    @Test
    fun `무승부 수익 금액을 계산한다`() {
        val actual = Profit(0)
        assertThat(betAmount.calculate(VerdictResult.DRAW)).isEqualTo(actual)
    }
}
