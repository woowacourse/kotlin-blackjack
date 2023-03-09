package domain

import blackjack.domain.BetAmount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BetAmountTest {
    @Test
    fun `베팅금액에 대해 더하기 연산자가 동일하게 작동한다`() {
        assertThat(BetAmount(1000) + BetAmount(2000)).isEqualTo(BetAmount(3000))
    }

    @Test
    fun `베팅금액에 대해 곱하기 연산자가 동일하게 작동한다`() {
        assertThat(BetAmount(1000) * 0.3).isEqualTo(BetAmount(300))
    }
}
