package blackjack

import blackjack.domain.BetAmount
import blackjack.domain.PlayerResultStatus
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BetAmountTest {
    @Test
    fun `배팅 금액에 음수가 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            BetAmount(-1)
        }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("배팅 금액은 0원 이상 입력해주세요.")
    }

    @Test
    fun `BLACKJACK_WIN 일때, 배팅 금액의 1․5배를 받는다`() {
        val betAmount = BetAmount(10000)

        betAmount.update(PlayerResultStatus.BLACKJACK_WIN)

        assertThat(betAmount.getAmount()).isEqualTo(15000)
    }

    @Test
    fun `PLAYER_WIN 일때, 배팅 금액 만큼 받는다`() {
        val betAmount = BetAmount(10000)

        betAmount.update(PlayerResultStatus.PLAYER_WIN)

        assertThat(betAmount.getAmount()).isEqualTo(10000)
    }

    @Test
    fun `PLAYER_LOSE 일때, 배팅 금액을 잃는다`() {
        val betAmount = BetAmount(10000)

        betAmount.update(PlayerResultStatus.PLAYER_LOSE)

        assertThat(betAmount.getAmount()).isEqualTo(-10000)
    }

    @Test
    fun `DRAW 일때, 배팅 금액을 돌려받는다`() {
        val betAmount = BetAmount(10000)

        betAmount.update(PlayerResultStatus.DRAW)

        assertThat(betAmount.getAmount()).isEqualTo(0)
    }
}
