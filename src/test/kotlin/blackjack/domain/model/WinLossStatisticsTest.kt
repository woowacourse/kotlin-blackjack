import blackjack.domain.model.Card
import blackjack.domain.model.WinLoss
import blackjack.domain.model.WinLossStatistics
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinLossStatisticsTest {
    @Test
    fun `손패 두 개를 비교하여 플레이어의 무승부임을 받아올 수 있다`() {
        val dealerBlackJackCards = listOf(Card(0), Card(11))
        val playerBlackJackCards = listOf(Card(0), Card(12))

        val winLossStatistics = WinLossStatistics()

        assertThat(winLossStatistics.calculatePlayerWinLoss(dealerBlackJackCards, playerBlackJackCards)).isEqualTo(WinLoss.DRAW)
    }

    @Test
    fun `손패 두 개를 비교하여 플레이어의 승리임을 받아올 수 있다`() {
        val dealerResult19Cards = listOf(Card(8), Card(10))
        val playerBlackJackCards = listOf(Card(0), Card(12))

        val winLossStatistics = WinLossStatistics()

        assertThat(winLossStatistics.calculatePlayerWinLoss(dealerResult19Cards, playerBlackJackCards)).isEqualTo(WinLoss.WIN)
    }

    @Test
    fun `손패 두 개를 비교하여 플레이어의 패배임을 받아올 수 있다`() {
        val dealerResult19Cards = listOf(Card(8), Card(10))
        val playerResult18Cards = listOf(Card(7), Card(12))

        val winLossStatistics = WinLossStatistics()

        assertThat(winLossStatistics.calculatePlayerWinLoss(dealerResult19Cards, playerResult18Cards)).isEqualTo(WinLoss.LOSE)
    }

    @Test
    fun `딜러의 전체 승 무 패 결과를 텍스트로 받아올 수 있다`() {
        val dealerBlackJackCards = listOf(Card(0), Card(11))
        val dealerResult19Cards = listOf(Card(8), Card(10))
        val playerBlackJackCards = listOf(Card(0), Card(12))
        val winLossStatistics = WinLossStatistics()
        winLossStatistics.calculatePlayerWinLoss(dealerBlackJackCards, playerBlackJackCards)
        winLossStatistics.calculatePlayerWinLoss(dealerResult19Cards, playerBlackJackCards)

        val expectedDealerWinLossText = "1무 1패"

        assertThat(winLossStatistics.getDealerWinLossText()).isEqualTo(expectedDealerWinLossText)
    }
}
