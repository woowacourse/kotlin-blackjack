package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetAmountTest {
    private val betAmount = BetAmount(10000)

    @Test
    fun `승리하고 블랙잭이 아닌 경우 베팅금만큼 받는다`() {
        // when
        val actual = betAmount.calculateProceed(GameResult.Win)
        val expected = 10000
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `승리하고 블랙잭인 경우 베팅금의 150%를 받는다`() {
        // when
        val actual = betAmount.calculateProceed(GameResult.BlackjackWin)
        val expected = 15000
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무승부인 경우 0원을 받는다`() {
        // when
        val actual = betAmount.calculateProceed(GameResult.Draw)
        val expected = 0
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `패배인 경우 베팅금을 잃는다`() {
        // when
        val actual = betAmount.calculateProceed(GameResult.Lose)
        val expected = -10000
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
