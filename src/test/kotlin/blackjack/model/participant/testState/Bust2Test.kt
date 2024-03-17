package blackjack.model.participant.testState

import blackjack.model.BattingMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class Bust2Test {
    @ParameterizedTest
    @ValueSource(ints = [0, 5, 10, 15, 20, 21])
    fun `상대방의 점수와는 관계없이, 수익률은 -(배팅금액) 이다`(opponentScore: Int) {
        val bust2 = Bust2()
        val profit = bust2.getProfit(22, opponentScore, BattingMoney.ofAmount(100))
        assertThat(profit.amount).isEqualTo(-100.0)
    }
}
