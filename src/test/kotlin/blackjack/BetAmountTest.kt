package blackjack

import blackjack.domain.BetAmount
import blackjack.domain.ResultStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetAmountTest {
    @Test
    fun `BLACKJACK_WIN 일때, 배팅 금액의 1․5배를 받는다`() {
        val betAmount = BetAmount(10000)

        betAmount.update(ResultStatus.BLACKJACK_WIN)

        assertThat(betAmount.getAmount()).isEqualTo(25000)
    }

    @Test
    fun `PLAYER_WIN 일때, 배팅 금액 만큼 받는다`() {
        val betAmount = BetAmount(10000)

        betAmount.update(ResultStatus.PLAYER_WIN)

        assertThat(betAmount.getAmount()).isEqualTo(20000)
    }

    @Test
    fun `PLAYER_LOSE 일때, 배팅 금액을 잃는다`() {
        val betAmount = BetAmount(10000)

        betAmount.update(ResultStatus.PLAYER_LOSE)

        assertThat(betAmount.getAmount()).isEqualTo(0)
    }

    @Test
    fun `DRAW 일때, 배팅 금액을 돌려받는다`() {
        val betAmount = BetAmount(10000)

        betAmount.update(ResultStatus.DRAW)

        assertThat(betAmount.getAmount()).isEqualTo(10000)
    }
}
