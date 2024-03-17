package blackjack.model.participant.testState

import blackjack.model.BattingMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StayTest {
    @Test
    fun `게임에서 이겼을 경우, 배팅 금액의 1배를 반환한다`() {
        val profit = Stay().getProfit(11, 1, BattingMoney.ofAmount(100.0))
        assertThat(profit.amount).isEqualTo(100.0 * 1)
    }

    @Test
    fun `게임에서 졌을 경우, 배팅 금액의 -1배를 반환한다`() {
        val profit = Stay().getProfit(10, 12, BattingMoney.ofAmount(100.0))
        assertThat(profit.amount).isEqualTo(100.0 * -1)
    }
}
