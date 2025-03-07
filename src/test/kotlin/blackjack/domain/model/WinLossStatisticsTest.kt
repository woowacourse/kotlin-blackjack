import blackjack.domain.model.Card
import blackjack.domain.model.WinLoss
import org.junit.jupiter.api.Test

class WinLossStatisticsTest {
    @Test
    fun `손패 두 개를 비교하여 플레이어의 무승부임을 받아올 수 있다`() {
        val dealerBlackJackCards = listOf(Card(0), Card(11))
        val playerBlackJackCards = listOf(Card(0), Card(12))

        val winLossStatistics = WinLossStatistics()

        assertThat(winLossStatistics.calcualtePlayerWinLoss(dealerBlackJackCards, playerBlackJackCards)).isEqualTo(WinLoss.DRAW)
    }

    @Test
    fun `손패 두 개를 비교하여 플레이어의 승리임을 받아올 수 있다`() {
        val dealerResult19Cards = listOf(Card(8), Card(10))
        val playerBlackJackCards = listOf(Card(0), Card(12))

        val winLossStatistics = WinLossStatistics()

        assertThat(winLossStatistics.calcualtePlayerWinLoss(dealerResult19Cards, playerBlackJackCards)).isEqualTo(WinLoss.WIN)
    }

    @Test
    fun `손패 두 개를 비교하여 플레이어의 패배임을 받아올 수 있다`() {
        val dealerResult19Cards = listOf(Card(8), Card(10))
        val playerResult18Cards = listOf(Card(7), Card(12))

        val winLossStatistics = WinLossStatistics()

        assertThat(winLossStatistics.calcualtePlayerWinLoss(dealerResult19Cards, playerResult18Cards)).isEqualTo(WinLoss.LOSE)
    }
}
