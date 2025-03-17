package blackjack.domain.model.betting

import blackjack.domain.model.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetAmountTest {
    @ParameterizedTest
    @ValueSource(
        doubles = [0.0, 1000.0, 50000.0],
    )
    fun `베팅 금액은 0원 이상이어야 한다`(actualAmount: Double) {
        assertDoesNotThrow {
            BetAmount(actualAmount)
        }
    }

    @Test
    fun `베팅 금액은 0원 미만 일 수 없다`() {
        val actualAmount = -1000.0

        assertThrows<IllegalArgumentException> {
            BetAmount(actualAmount)
        }
    }

    @Test
    fun `블랙잭 승리의 결과를 받으면 150% 수익금을 반환한다`() {
        val betAmount = BetAmount(1000.0)
        val gameResult = GameResult.BLACKJACK_WIN

        val actualProfit = betAmount.toProfit(gameResult)

        val expectedProfit = 1500.0

        assertThat(actualProfit).isEqualTo(expectedProfit)
    }

    @Test
    fun `일반 승리의 결과를 받으면 100% 수익금을 반환한다`() {
        val betAmount = BetAmount(1000.0)
        val gameResult = GameResult.WIN

        val actualProfit = betAmount.toProfit(gameResult)

        val expectedProfit = 1000.0

        assertThat(actualProfit).isEqualTo(expectedProfit)
    }

    @Test
    fun `무승부의 결과를 받으면 0% 수익금을 반환한다`() {
        val betAmount = BetAmount(1000.0)
        val gameResult = GameResult.DRAW

        val actualProfit = betAmount.toProfit(gameResult)

        val expectedProfit = 0.0

        assertThat(actualProfit).isEqualTo(expectedProfit)
    }

    @Test
    fun `패배의 결과를 받으면 -100% 수익금을 반환한다`() {
        val betAmount = BetAmount(1000.0)
        val gameResult = GameResult.LOSE

        val actualProfit = betAmount.toProfit(gameResult)

        val expectedProfit = -1000.0

        assertThat(actualProfit).isEqualTo(expectedProfit)
    }
}
