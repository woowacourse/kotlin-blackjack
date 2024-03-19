package blackjack.model.participant.state

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackTest {
    @Test
    fun `블랙잭의 타입은 Finish다`() {
        val blackjack = Blackjack()
        assertThat(blackjack is Finish).isTrue
    }

    @Test
    fun `상대방과 점수가 같을 경우, 동점의 수익률을 반환한다`() {
        val blackjack = Blackjack()
        val profit = blackjack.getProfit(21, 21, BattingMoney.ofAmount(100))
        assertThat(profit.amount).isEqualTo(100 * CompetitionResult.SAME.profit)
    }

    @Test
    fun `상대방과 점수가 다를 경우, 블랙잭의 수익률을 반환한다`() {
        val blackjack = Blackjack()
        val profit = blackjack.getProfit(21, 20, BattingMoney.ofAmount(100))
        assertThat(profit.amount).isEqualTo(100 * CompetitionResult.BLACKJACK.profit)
    }
}
