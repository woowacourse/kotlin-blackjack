package blackjack.domain.model.progress

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Number
import blackjack.domain.model.participant.Player
import org.junit.jupiter.api.Test

class ProfitStatisticsTest {
    @Test
    fun `참가자별 수익을 계산한다`() {
        val meda = Player("메다", listOf(Card(Number.TEN), Card(Number.NINE))) // NORMAL
        val cream = Player("크림", List(3) { Card(Number.TEN) }) // BUST
        val peto = Player("페토", listOf(Card(Number.QUEEN), Card(Number.ACE))) // BLACKJACK

        val betHistory = BetHistory(mutableMapOf(meda to 1000, cream to 2000, peto to 3000))
        val winLossStatistics =
            WinLossStatistics(
                _playerWinLoseInfo =
                    mutableMapOf(
                        meda to WinLoss.WIN,
                        cream to WinLoss.LOSE,
                        peto to WinLoss.WIN,
                    ),
            )

        val profitStatistics = ProfitStatistics(betHistory, winLossStatistics)
        assertThat(profitStatistics.profits).equalTo(mapOf(meda to 1000, cream to 0, peto to 4500))
    }
}
