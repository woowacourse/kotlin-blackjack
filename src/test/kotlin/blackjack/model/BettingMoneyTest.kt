package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BettingMoneyTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 300_000_001])
    fun `베팅 금액이 1원 이상 최대 3억원이 아닐 경우 예외를 발생시킨다`(money: Int) {
        assertThrows<IllegalArgumentException> { BettingMoney(money) }
    }

    @Test
    fun `게임 결과를 기반으로 수익금을 반환한다`() {
        // given:
        val bettingMoney = BettingMoney(1_000)
        val gameResult = GameResult.WIN

        // when:
        val actual: Profit = bettingMoney.profit(gameResult)

        // then:
        assertThat(actual).isEqualTo(Profit(1_000.0))
    }
}
